package com.pj_module5.controller.product;

import com.pj_module5.model.Product;
import com.pj_module5.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductUserController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getTrueStatusProduct(
            @RequestParam(name = "search", defaultValue = "") String search
    ){
        if (search.isEmpty()){
            return new ResponseEntity<>(productService.findByStatus(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(productService.findAllByProductNameAndStatusTrue(search), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") String id){
        try {
            Product product = productService.findById(Long.valueOf(id));
            if (product != null){
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }catch (Exception ignored){
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getProductByCategoryId(@PathVariable("id") String categoryId){
        try {
           List<Product> products = productService.findAllByCategory(Long.valueOf(categoryId));
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
}
