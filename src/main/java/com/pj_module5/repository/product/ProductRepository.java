package com.pj_module5.repository.product;

import com.pj_module5.model.Category;
import com.pj_module5.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStatus(boolean status);
    List<Product> findAllByProductNameContainsIgnoreCaseAndStatus(String productName,boolean status);
    List<Product> findAllByProductNameContainsIgnoreCase(String productName);
    List<Product> findAllByCategory(Category category);
}
