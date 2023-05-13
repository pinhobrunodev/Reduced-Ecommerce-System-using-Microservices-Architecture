package com.pinhobrunodev.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {
    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
    private ProductDetails productDetails;
}
