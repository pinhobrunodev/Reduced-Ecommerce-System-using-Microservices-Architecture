package com.pinhobrunodev.OrderService.service;

import com.pinhobrunodev.OrderService.entity.Order;
import com.pinhobrunodev.OrderService.exception.CustomException;
import com.pinhobrunodev.OrderService.external.client.PaymentService;
import com.pinhobrunodev.OrderService.external.client.ProductService;
import com.pinhobrunodev.OrderService.external.request.PaymentRequest;
import com.pinhobrunodev.OrderService.helper.Constants;
import com.pinhobrunodev.OrderService.model.OrderResponse;
import com.pinhobrunodev.OrderService.model.PlaceOrderRequest;
import com.pinhobrunodev.OrderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public Long placeOrder(PlaceOrderRequest placeOrderRequest) {

        log.info("Placing Order Request: {}", placeOrderRequest);
        reduceProductQuantity(placeOrderRequest);
        log.info("Creating Order with Status CREATED");
        var order = Order.builder()
                .amount(placeOrderRequest.getTotalAmount())
                .orderStatus(Constants.ORDER_CREATED)
                .productId(placeOrderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(placeOrderRequest.getQuantity())
                .build();
        order = orderRepository.save(order);
        doPayment(order, placeOrderRequest);
        log.info("Order Places successfully with Order Id: {}", order.getId());
        return order.getId();
    }

    @Override
    public void reduceProductQuantity(PlaceOrderRequest placeOrderRequest) {
        log.info("Calling PRODUCT-SERVICE to validate product quantity...");
        productService.reduceQuantity(placeOrderRequest.getProductId(), placeOrderRequest.getQuantity());
    }

    @Override
    public Long doPayment(Order order, PlaceOrderRequest placeOrderRequest) {
        log.info("Calling Payment Service to complete the payment");
        PaymentRequest paymentRequest =
                PaymentRequest
                        .builder()
                        .amount(placeOrderRequest.getTotalAmount())
                        .orderId(order.getId())
                        .paymentMode(placeOrderRequest.getPaymentMode())
                        .referenceNumber(UUID.randomUUID().toString())
                        .build();
        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done Successfully. Changing the Order Status to PLACED");
            orderStatus = Constants.ORDER_PLACED;
        } catch (Exception e) {
            log.error("Error occurred in payment. Changing the Order Status to PAYMENT_FAILED");
            orderStatus = Constants.ORDER_FAILED;
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get order details for Order Id: {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException("Order not found for the given Id:" + orderId, Constants.NOT_FOUND, 404)
        );
        return OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderDate())
                .amount(order.getAmount())
                .build();
    }
}
