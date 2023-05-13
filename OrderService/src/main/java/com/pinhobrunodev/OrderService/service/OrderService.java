package com.pinhobrunodev.OrderService.service;

import com.pinhobrunodev.OrderService.entity.Order;
import com.pinhobrunodev.OrderService.model.OrderResponse;
import com.pinhobrunodev.OrderService.model.PlaceOrderRequest;
import com.pinhobrunodev.OrderService.model.ProductDetails;

public interface OrderService {
    Long placeOrder(PlaceOrderRequest placeOrderRequest);

    void reduceProductQuantity (PlaceOrderRequest placeOrderRequest);

    void doPayment(Order order, PlaceOrderRequest placeOrderRequest);

    OrderResponse getOrderDetails(long orderId);

    ProductDetails fetchProductDetails(long productId);

}
