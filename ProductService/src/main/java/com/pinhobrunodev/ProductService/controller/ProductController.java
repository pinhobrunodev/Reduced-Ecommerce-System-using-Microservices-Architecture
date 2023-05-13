package com.pinhobrunodev.ProductService.controller;

import com.pinhobrunodev.ProductService.model.ProductByIdResponse;
import com.pinhobrunodev.ProductService.model.ProductRequest;
import com.pinhobrunodev.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.addProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductByIdResponse> getProductById(@PathVariable Long id){
        return new ResponseEntity<ProductByIdResponse>(productService.getProductById(id),HttpStatus.OK);
    }
}
