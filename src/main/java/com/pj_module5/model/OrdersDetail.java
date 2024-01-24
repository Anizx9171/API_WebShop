package com.pj_module5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    Orders orders;
}