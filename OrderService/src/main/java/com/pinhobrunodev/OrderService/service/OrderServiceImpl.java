package com.pinhobrunodev.OrderService.service;

import com.pinhobrunodev.OrderService.entity.Order;
import com.pinhobrunodev.OrderService.external.client.ProductService;
import com.pinhobrunodev.OrderService.model.PlaceOrderRequest;
import com.pinhobrunodev.OrderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;


    @Override
    public Long placeOrder(PlaceOrderRequest placeOrderRequest) {

        log.info("Placing Order Request: {}", placeOrderRequest);

        log.info("Calling PRODUCT-SERVICE to validate product quantity...");
        productService.reduceQuantity(placeOrderRequest.getProductId(), placeOrderRequest.getQuantity());

        log.info("Creating Order with Status CREATED");
        var order = Order.builder()
                .amount(placeOrderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(placeOrderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(placeOrderRequest.getQuantity())
                .build();
        order = orderRepository.save(order);
        log.info("Order Places successfully with Order Id: {}", order.getId());
        return order.getId();
    }
}
