package com.pj_module5.controller.category;

import com.pj_module5.model.Category;
import com.pj_module5.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryAdminController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(name = "search", defaultValue = "") String search
    ){
        if (search.isEmpty()){
            List<Category> categories = categoryService.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        else {
            List<Category> categories = categoryService.findAllByCategoryName(search);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCateById(@PathVariable("id") Long id){
        Category cate = categoryService.findById(id);
        if (cate != null){
            return new ResponseEntity<>(cate, HttpStatus.OK);
        }
        return new ResponseEntity<>("no content", HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Category category){
       try {
           Category cate = categoryService.save(category);
           return new ResponseEntity<>(cate, HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>("tao moi khong thanh cong", HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Category category, @PathVariable("id") Long id){
        Category cate = categoryService.findById(id);
        if (cate != null){
            cate.setCategoryName(category.getCategoryName());
            cate.setStatus(category.getStatus());
            return new ResponseEntity<>(cate, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("err", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            categoryService.deleteById(id);
            return new ResponseEntity<>("Delete success", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Delete error", HttpStatus.BAD_REQUEST);
        }
    }
}
