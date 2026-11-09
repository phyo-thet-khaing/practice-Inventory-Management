package com.ptk.demo.dto.request;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductMultipartRequest {

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @NotBlank(message = "Product name is required")
    private String name;

    private String unit;

    @NotNull(message = "Product price is required")
    @PositiveOrZero(message = "Price must be zero or greater")
    private BigDecimal price;

    @Schema(
        type = "string",
        format = "binary",
        description = "Optional product image"
    )
    private MultipartFile image;
}