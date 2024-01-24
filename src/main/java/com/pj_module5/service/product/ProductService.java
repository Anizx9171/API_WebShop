package com.pj_module5.service.product;

import com.pj_module5.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findByStatus(Boolean status);
    List<Product> findAllByProductNameAndStatusTrue(String name);
    List<Product> findAllByProductName(String name);

    Product findById(Long id);
    List<Product> findAllByCategory(Long catId);

    Product save(Product product);
    void deleteById(Long id);
}