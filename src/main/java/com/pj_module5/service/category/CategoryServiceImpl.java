package com.pj_module5.service.category;

import com.pj_module5.model.Category;
import com.pj_module5.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findByStatus(Boolean status) {
        return categoryRepository.findAllByStatus(status);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllByCategoryName(String name) {
        return categoryRepository.findAllByCategoryNameContainsIgnoreCase(name);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllByCategoryNameAndStatusTrue(String name) {
        return categoryRepository.findAllByCategoryNameContainsIgnoreCaseAndStatus(name, true);
    }

    @Override
    public void deleteById(Long id) {
        if (categoryRepository.findById(id).isPresent()){
            categoryRepository.deleteById(id);
        }
    }
}
