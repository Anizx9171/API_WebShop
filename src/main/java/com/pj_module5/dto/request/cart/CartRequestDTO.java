package com.pj_module5.dto.request.cart;

import com.pj_module5.model.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartRequestDTO {
   @NotNull(message = "User ID cannot be null")
   private Long userId;
   @Valid
   @NotNull(message = "Products list cannot be null")
   private List<CurProductRequest> products;
   @Positive(message = "Total price must be a positive value")
   private Double totalPrice;
}