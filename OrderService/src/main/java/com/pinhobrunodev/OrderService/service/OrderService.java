package com.pinhobrunodev.OrderService.service;

import com.pinhobrunodev.OrderService.model.PlaceOrderRequest;

public interface OrderService {
    Long placeOrder(PlaceOrderRequest placeOrderRequest);
}
