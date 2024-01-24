package com.pj_module5.controller.product;

import com.pj_module5.dto.request.product.ProductRequestDTO;
import com.pj_module5.model.Category;
import com.pj_module5.model.Product;
import com.pj_module5.service.category.CategoryService;
import com.pj_module5.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class ProductAdminController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Product>> findAllProduct(
            @RequestParam(name = "search", defaultValue = "") String search
    ) {
        if (search.isEmpty()) {
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(productService.findAllByProductName(search), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO,  BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            List<String> errorMess = new ArrayList<>();
            for (ObjectError objectError :objectErrors) {
                errorMess.add(objectError.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMess, HttpStatus.BAD_REQUEST);
        }
        try {
            Category category = categoryService.findById(productRequestDTO.getCategoryId());
            if (category != null){
                Product product = new Product();
                product.setProductName(productRequestDTO.getProductName());
                product.setPrice(productRequestDTO.getPrice());
                product.setImageUrl(productRequestDTO.getImageUrl());
                product.setStock(productRequestDTO.getStock());
                product.setCategory(category);
                Product proSaved = productService.save(product);
                return new  ResponseEntity<>(proSaved, HttpStatus.CREATED);
            }
        }catch (Exception ignored){
        }
        return new ResponseEntity<>("sai cmnr", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequestDTO productRequestDTO, @PathVariable("id") String id){
        try {
            Product product = productService.findById(Long.valueOf(id));
            if (product != null){
                Category category = categoryService.findById(productRequestDTO.getCategoryId());
                if (category != null){
                    product.setProductName(productRequestDTO.getProductName());
                    product.setPrice(productRequestDTO.getPrice());
                    product.setImageUrl(productRequestDTO.getImageUrl());
                    product.setStock(productRequestDTO.getStock());
                    product.setCategory(category);
                    product.setStatus(productRequestDTO.isStatus());
                    Product proSaved = productService.save(product);
                    return new  ResponseEntity<>(proSaved, HttpStatus.CREATED);
                }
            }
        }catch (Exception ignored){
        }
        return new ResponseEntity<>("sai cmnr", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") String id){
        try {
            productService.deleteById(Long.valueOf(id));
            return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
        }catch (Exception ignored){
        }
        return new ResponseEntity<>("Delete error", HttpStatus.NO_CONTENT);
    }
    
}
