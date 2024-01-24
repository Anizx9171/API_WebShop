package com.pj_module5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name cannot be blank")
    @Column(unique = true)
    private String categoryName;

    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;
}
