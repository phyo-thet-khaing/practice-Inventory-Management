
package com.ptk.demo.dto.request;

import java.math.BigDecimal;

import com.ptk.demo.enums.ProductStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

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

    @Builder.Default
    private ProductStatus status = ProductStatus.ACTIVE;
}
