package com.pj_module5.service.category;

import com.pj_module5.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findByStatus(Boolean status);
    List<Category> findAllByCategoryNameAndStatusTrue(String name);
    List<Category> findAllByCategoryName(String name);
    Category findById(Long id);
    Category save(Category category);
    void deleteById(Long id);
}