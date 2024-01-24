package com.pj_module5.service.product;

import com.pj_module5.model.Category;
import com.pj_module5.model.Product;
import com.pj_module5.repository.product.ProductRepository;
import com.pj_module5.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByStatus(Boolean status) {
        return productRepository.findAllByStatus(status);
    }

    @Override
    public List<Product> findAllByProductNameAndStatusTrue(String name) {
        return productRepository.findAllByProductNameContainsIgnoreCaseAndStatus(name, true);
    }

    @Override
    public List<Product> findAllByProductName(String name) {
        return productRepository.findAllByProductNameContainsIgnoreCase(name);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAllByCategory(Long catId) {
        Category category = categoryService.findById(catId);
        if (category != null){
            return productRepository.findAllByCategory(category);
        }
        return new ArrayList<>();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}