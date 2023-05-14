package com.pinhobrunodev.OrderService.external.client;

import com.pinhobrunodev.OrderService.exception.CustomException;
import com.pinhobrunodev.OrderService.external.response.ProductByIdResponse;
import com.pinhobrunodev.OrderService.helper.Constants;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(value = "PRODUCT-SERVICE/product")
public interface ProductService {
    @PutMapping("/reduceQuantity/{id}")
    void reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);

    @GetMapping(value = "/{id}")
    ProductByIdResponse getProductDetails(@PathVariable Long id);

    default void fallback(Exception e) {
        throw new CustomException(
                Constants.PAYMENT_SVC_NOT_AVAILABLE,
                Constants.UNAVAILABLE,
                500
        );
    }
}
