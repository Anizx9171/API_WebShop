package com.pj_module5.dto.request.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequestDTO {
    @NotBlank(message = "Product name cannot be blank")
    private String productName;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive value")
    private Double price;
    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock must be a non-negative value")
    private Integer stock;
    @NotNull(message = "Category ID cannot be null")
    @Positive(message = "Category ID must be a positive value")
    private Long categoryId;
    @NotBlank(message = "Product image cannot be blank")
    private String imageUrl;
    private boolean status;
}