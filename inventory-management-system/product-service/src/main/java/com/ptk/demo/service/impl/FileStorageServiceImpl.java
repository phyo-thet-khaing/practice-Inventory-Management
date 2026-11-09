
package com.ptk.demo.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ptk.demo.exception.FileStorageException;
import com.ptk.demo.model.Image;
import com.ptk.demo.repository.ImageRepository;
import com.ptk.demo.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private static final String UPLOAD_DIR = "uploads/products";

    private static final String FILE_PATH_PREFIX =
            "/uploads/products/";

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );

    private final ImageRepository imageRepository;

    public FileStorageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image getOrCreateImage(MultipartFile file) {

        validateImage(file);

        String hash = generateHash(file);

        return imageRepository.findByHash(hash)
                .orElseGet(() -> createNewImage(file, hash));
    }

    private Image createNewImage(MultipartFile file, String hash) {

        Path filePath = null;

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR)
                    .toAbsolutePath()
                    .normalize();

            Files.createDirectories(uploadPath);

            String originalFilename = file.getOriginalFilename();

            if (originalFilename == null || originalFilename.isBlank()) {
                originalFilename = "image";
            }

            String safeFilename = Paths.get(originalFilename)
                    .getFileName()
                    .toString();

            String storedName = UUID.randomUUID()
                    + "_"
                    + safeFilename;

            filePath = uploadPath.resolve(storedName).normalize();

            if (!filePath.startsWith(uploadPath)) {
                throw new FileStorageException(
                        "Invalid image file path");
            }

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            Image image = new Image();
            image.setFileName(safeFilename);
            image.setFilePath(FILE_PATH_PREFIX + storedName);
            image.setHash(hash);

            try {
                Image savedImage = imageRepository.save(image);

                // Ensure the generated database ID is available.
                imageRepository.flush();

                return savedImage;

            } catch (RuntimeException e) {
                Files.deleteIfExists(filePath);
                throw e;
            }

        } catch (IOException e) {
            throw new FileStorageException(
                    "Failed to store image file", e);
        }
    }

    @Override
    public void deletePhysicalFile(String filePath) {

        if (filePath == null || filePath.isBlank()) {
            throw new FileStorageException(
                    "Image file path is required");
        }

        String normalizedPath = filePath.replace('\\', '/');

        if (!normalizedPath.startsWith(FILE_PATH_PREFIX)) {
            throw new FileStorageException(
                    "Invalid image file path");
        }

        String storedName =
                normalizedPath.substring(FILE_PATH_PREFIX.length());

        if (storedName.isBlank()
                || storedName.contains("/")
                || storedName.equals(".")
                || storedName.equals("..")) {
            throw new FileStorageException(
                    "Invalid image filename");
        }

        try {
            Path uploadRoot = Paths.get(UPLOAD_DIR)
                    .toAbsolutePath()
                    .normalize();

            Path target = uploadRoot.resolve(storedName).normalize();

            if (!target.startsWith(uploadRoot)) {
                throw new FileStorageException(
                        "Image path is outside upload directory");
            }

            Files.deleteIfExists(target);

        } catch (IOException e) {
            throw new FileStorageException(
                    "Failed to delete image file", e);
        }
    }

    private String generateHash(MultipartFile file) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = digest.digest(file.getBytes());

            StringBuilder hash = new StringBuilder();

            for (byte b : hashBytes) {
                hash.append(String.format("%02x", b & 0xff));
            }

            return hash.toString();

        } catch (NoSuchAlgorithmException | IOException e) {
            throw new FileStorageException(
                    "Failed to generate image hash", e);
        }
    }

    private void validateImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new FileStorageException(
                    "Product image is required");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileStorageException(
                    "Image size must not exceed 10MB");
        }

        String contentType = file.getContentType();

        if (contentType == null
                || !ALLOWED_TYPES.contains(contentType)) {
            throw new FileStorageException(
                    "Only JPG, PNG and WEBP images are allowed");
        }
    }
}
