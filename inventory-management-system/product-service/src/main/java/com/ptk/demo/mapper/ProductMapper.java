
package com.ptk.demo.mapper;

import org.springframework.stereotype.Component;

import com.ptk.demo.dto.request.ProductRequest;
import com.ptk.demo.dto.response.ProductResponse;
import com.ptk.demo.model.Product;

@Component
public class ProductMapper {

    // Convert ProductRequest to Product Entity
    public static Product toEntity(ProductRequest request) {

        if (request == null) {
            return null;
        }

        return Product.builder()
                .categoryId(request.getCategoryId())
                .supplierId(request.getSupplierId())
                .name(request.getName())
                .unit(request.getUnit())
                .price(request.getPrice())
                .build();
    }

    // Convert Product Entity to ProductResponse
    public static ProductResponse toResponse(Product product) {

        if (product == null) {
            return null;
        }

        Long imageId = null;
        String imageUrl = null;

        if (product.getImage() != null) {
            imageId = product.getImage().getId();
            imageUrl = product.getImage().getFilePath();
        }

        return ProductResponse.builder()
                .id(product.getId())
                .categoryId(product.getCategoryId())
                .supplierId(product.getSupplierId())
                .name(product.getName())
                .unit(product.getUnit())
                .price(product.getPrice())
                .imageId(imageId)
                .imageUrl(imageUrl)
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
