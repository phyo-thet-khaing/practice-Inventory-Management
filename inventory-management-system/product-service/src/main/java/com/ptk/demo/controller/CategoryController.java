
package com.ptk.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptk.demo.dto.request.CategoryRequest;
import com.ptk.demo.dto.response.CategoryResponse;
import com.ptk.demo.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(
        name = "Category Management",
        description = "APIs for managing categories"
)
public class CategoryController {

    private final CategoryService categoryService;

    // =========================
    // CREATE
    // =========================

    @Operation(
            summary = "Create Category",
            description = "Create a new category"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Category created successfully"
    )
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse response =
                categoryService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // =========================
    // GET ALL
    // =========================

    @Operation(
            summary = "Get All Categories",
            description = "Retrieve all categories"
    )
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {

        return ResponseEntity.ok(
                categoryService.getAll()
        );
    }

    // =========================
    // GET BY ID
    // =========================

    @Operation(
            summary = "Get Category By ID",
            description = "Retrieve a category by ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @Parameter(description = "Category ID")
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoryService.getById(id)
        );
    }

    // =========================
    // UPDATE
    // =========================

    @Operation(
            summary = "Update Category",
            description = "Update category information"
    )
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {

        return ResponseEntity.ok(
                categoryService.update(id, request)
        );
    }

    // =========================
    // DELETE
    // =========================

    @Operation(
            summary = "Delete Category",
            description = "Delete category by ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long id) {

        categoryService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}

