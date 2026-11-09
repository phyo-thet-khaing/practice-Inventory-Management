
package com.ptk.demo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ptk.demo.enums.ProductStatus;

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
public class ProductResponse {

    private Long id;

    private Long categoryId;

    private Long supplierId;

    private String name;

    private String unit;

    private BigDecimal price;

    private Long imageId;

    private String imageUrl;

    private ProductStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
