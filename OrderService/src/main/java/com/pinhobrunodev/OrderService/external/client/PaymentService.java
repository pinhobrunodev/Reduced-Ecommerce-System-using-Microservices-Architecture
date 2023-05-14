package com.pinhobrunodev.OrderService.external.client;

import com.pinhobrunodev.OrderService.external.request.PaymentRequest;
import com.pinhobrunodev.OrderService.external.response.PaymentByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "PAYMENT-SERVICE/payment")
public interface PaymentService {
    @PostMapping
    void doPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping(value = "/order/{orderId}")
    PaymentByIdResponse getPaymentDetails(@PathVariable long orderId);
}
