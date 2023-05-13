package com.pinhobrunodev.OrderService.controller;

import com.pinhobrunodev.OrderService.model.PlaceOrderRequest;
import com.pinhobrunodev.OrderService.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest) {
        var orderId = orderService.placeOrder(placeOrderRequest);
        log.info("Order Id: {}", orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
}
