package com.ptk.demo.mapper;

import org.springframework.stereotype.Component;

import com.ptk.demo.dto.request.CategoryRequest;
import com.ptk.demo.dto.response.CategoryResponse;
import com.ptk.demo.model.Category;

@Component
public class CategoryMapper {

    // Convert CategoryRequest to Category Entity
    public static Category toEntity(CategoryRequest request) {

        if (request == null) {
            return null;
        }

        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    // Convert Category Entity to CategoryResponse
    public static CategoryResponse toResponse(Category category) {

        if (category == null) {
            return null;
        }

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}