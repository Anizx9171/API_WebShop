package com.pj_module5.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CurProductRequest {
    private Long id;
    private Double price;
    private Integer stock;
}
