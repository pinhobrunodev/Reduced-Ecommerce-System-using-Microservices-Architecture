package com.pinhobrunodev.OrderService.service;

import com.pinhobrunodev.OrderService.entity.Order;
import com.pinhobrunodev.OrderService.model.PlaceOrderRequest;

public interface OrderService {
    Long placeOrder(PlaceOrderRequest placeOrderRequest);

    void reduceProductQuantity (PlaceOrderRequest placeOrderRequest);

    Long doPayment(Order order,PlaceOrderRequest placeOrderRequest);
}
