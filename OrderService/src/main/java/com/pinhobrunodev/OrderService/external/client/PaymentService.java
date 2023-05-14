package com.pinhobrunodev.OrderService.external.client;

import com.pinhobrunodev.OrderService.exception.CustomException;
import com.pinhobrunodev.OrderService.external.request.PaymentRequest;
import com.pinhobrunodev.OrderService.external.response.PaymentByIdResponse;
import com.pinhobrunodev.OrderService.helper.Constants;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
@CircuitBreaker(name = "external",fallbackMethod = "fallback")
@FeignClient(value = "PAYMENT-SERVICE/payment")
public interface PaymentService {
    @PostMapping
    void doPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping(value = "/order/{orderId}")
    PaymentByIdResponse getPaymentDetails(@PathVariable long orderId);

    default void fallback(Exception e){
        throw  new CustomException(
                Constants.PAYMENT_SVC_NOT_AVAILABLE,
                Constants.UNAVAILABLE,
                500
        );
    }
}
