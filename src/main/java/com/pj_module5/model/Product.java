package com.pj_module5.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String productName;
    private Double price;
    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    private String imageUrl;
}
