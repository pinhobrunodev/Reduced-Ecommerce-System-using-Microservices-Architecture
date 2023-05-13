package com.pinhobrunodev.ProductService.service;

import com.pinhobrunodev.ProductService.model.ProductByIdResponse;
import com.pinhobrunodev.ProductService.model.ProductRequest;

public interface ProductService {
    Long addProduct(ProductRequest productRequest);

    ProductByIdResponse getProductById(Long id);
}
