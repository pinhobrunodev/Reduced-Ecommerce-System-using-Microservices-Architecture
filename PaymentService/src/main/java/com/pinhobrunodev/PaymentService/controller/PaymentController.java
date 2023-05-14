package com.pinhobrunodev.PaymentService.controller;

import com.pinhobrunodev.PaymentService.model.PaymentByIdResponse;
import com.pinhobrunodev.PaymentService.model.PaymentRequest;
import com.pinhobrunodev.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.doPayment(paymentRequest));
    }
    @GetMapping(value = "/order/{orderId}")
    public ResponseEntity<PaymentByIdResponse> getPaymentDetails(@PathVariable long orderId){
        return ResponseEntity.ok(paymentService.getPaymentDetails(orderId));
    }
}
