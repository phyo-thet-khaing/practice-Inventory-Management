
package com.ptk.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptk.demo.dto.request.ProductMultipartRequest;
import com.ptk.demo.dto.response.ProductResponse;
import com.ptk.demo.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@Tag(
    name = "Product Management",
    description = "APIs for managing products"
)
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE PRODUCT WITH OPTIONAL IMAGE
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
        summary = "Create Product",
        description = "Create a product with optional image using multipart form-data"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Product created successfully"
    )
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @ModelAttribute ProductMultipartRequest request) {

        ProductResponse response = productService.create(
                request,
                request.getImage()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL PRODUCTS
    @GetMapping
    @Operation(summary = "Get All Products")
    @ApiResponse(
        responseCode = "200",
        description = "Products retrieved successfully"
    )
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        return ResponseEntity.ok(productService.getAll());
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Get Product By ID")
    @ApiResponse(
        responseCode = "200",
        description = "Product retrieved successfully"
    )
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(productService.getById(id));
    }

    // UPDATE PRODUCT WITH OPTIONAL IMAGE
    @PutMapping(
        value = "/{id}",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Operation(
        summary = "Update Product",
        description = "Update product details and optionally replace its image"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Product updated successfully"
    )
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute ProductMultipartRequest request) {

        ProductResponse response = productService.update(
                id,
                request,
                request.getImage()
        );

        return ResponseEntity.ok(response);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product")
    @ApiResponse(
        responseCode = "204",
        description = "Product deleted successfully"
    )
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
