
package com.ptk.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import com.ptk.demo.dto.request.ProductMultipartRequest;
import com.ptk.demo.dto.response.ProductResponse;
import com.ptk.demo.exception.ResourceNotFoundException;
import com.ptk.demo.mapper.ProductMapper;
import com.ptk.demo.model.Image;
import com.ptk.demo.model.Product;
import com.ptk.demo.repository.ImageRepository;
import com.ptk.demo.repository.ProductRepository;
import com.ptk.demo.service.FileStorageService;
import com.ptk.demo.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger log =
            LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public ProductResponse create(
            ProductMultipartRequest request,
            MultipartFile image) {

        validateRequest(request);

        Product product = new Product();

        product.setCategoryId(request.getCategoryId());
        product.setSupplierId(request.getSupplierId());
        product.setName(request.getName());
        product.setUnit(request.getUnit());
        product.setPrice(request.getPrice());

        if (image != null && !image.isEmpty()) {
            Image imageEntity =
                    fileStorageService.getOrCreateImage(image);

            product.setImage(imageEntity);
        }

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponse(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAll() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id));

        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponse update(
            Long id,
            ProductMultipartRequest request,
            MultipartFile image) {

        validateRequest(request);

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id));

        product.setCategoryId(request.getCategoryId());
        product.setSupplierId(request.getSupplierId());
        product.setName(request.getName());
        product.setUnit(request.getUnit());
        product.setPrice(request.getPrice());

        if (image != null && !image.isEmpty()) {
            Image newImage =
                    fileStorageService.getOrCreateImage(image);

            product.setImage(newImage);
        }

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toResponse(updatedProduct);
    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id));

        Image image = product.getImage();

        productRepository.delete(product);
        productRepository.flush();

        if (image == null) {
            return;
        }

        boolean imageStillUsed =
                productRepository.existsByImage_Id(image.getId());

        if (imageStillUsed) {
            return;
        }

        String filePath = image.getFilePath();

        imageRepository.delete(image);
        imageRepository.flush();

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronization() {

                    @Override
                    public void afterCommit() {
                        try {
                            fileStorageService.deletePhysicalFile(filePath);
                        } catch (RuntimeException e) {
                            log.error(
                                    "Failed to delete unused image file: {}",
                                    filePath,
                                    e);
                        }
                    }
                });
    }

    private void validateRequest(ProductMultipartRequest request) {

        if (request == null) {
            throw new IllegalArgumentException(
                    "Product request is required");
        }

        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException(
                    "categoryId is required");
        }

        if (request.getSupplierId() == null) {
            throw new IllegalArgumentException(
                    "supplierId is required");
        }
    }
}
