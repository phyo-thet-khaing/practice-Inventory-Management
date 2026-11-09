package com.ptk.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.ptk.demo.model.Image;

public interface FileStorageService {

    Image getOrCreateImage(MultipartFile file);
    
    void deletePhysicalFile(String filePath);
}