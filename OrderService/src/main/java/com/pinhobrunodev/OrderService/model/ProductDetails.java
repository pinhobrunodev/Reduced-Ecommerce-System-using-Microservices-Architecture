package com.pinhobrunodev.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDetails {
    private String productName;
    private long productId;
    private long quantity;
    private long price;
}
