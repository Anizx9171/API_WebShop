package com.pj_module5.controller.category;

import com.pj_module5.model.Category;
import com.pj_module5.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryUserController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public ResponseEntity<?> getCategoryStatusTrue(
            @RequestParam(name = "search", defaultValue = "") String search
    ){
        if (search.isEmpty()){
           List<Category> categories = categoryService.findByStatus(true);
           return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        else {
            List<Category> categories = categoryService.findAllByCategoryNameAndStatusTrue(search);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
    }
}
