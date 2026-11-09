
package com.ptk.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ptk.demo.dto.request.ProductMultipartRequest;
import com.ptk.demo.dto.response.ProductResponse;

public interface ProductService {

    ProductResponse create(
            ProductMultipartRequest request,
            MultipartFile image
    );

    List<ProductResponse> getAll();

    ProductResponse getById(Long id);

    ProductResponse update(
            Long id,
            ProductMultipartRequest request,
            MultipartFile image
    );

    void delete(Long id);
}
