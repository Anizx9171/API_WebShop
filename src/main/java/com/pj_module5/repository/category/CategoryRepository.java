package com.pj_module5.repository.category;

import com.pj_module5.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByCategoryNameContainsIgnoreCase(String name);
    List<Category> findAllByStatus(Boolean status);

    List<Category> findAllByCategoryNameContainsIgnoreCaseAndStatus(String name, Boolean status);
}
