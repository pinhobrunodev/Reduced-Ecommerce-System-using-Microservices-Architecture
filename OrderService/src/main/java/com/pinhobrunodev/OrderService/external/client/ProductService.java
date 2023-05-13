package com.pinhobrunodev.OrderService.external.client;

import com.pinhobrunodev.OrderService.external.response.ProductByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "PRODUCT-SERVICE/product")
public interface ProductService {
    @PutMapping("/reduceQuantity/{id}")
    void reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);

    @GetMapping(value = "/{id}")
    ProductByIdResponse getProductById(@PathVariable Long id);
}
