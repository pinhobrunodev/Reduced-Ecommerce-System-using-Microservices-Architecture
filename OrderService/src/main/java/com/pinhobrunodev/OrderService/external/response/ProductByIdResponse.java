package com.pinhobrunodev.OrderService.external.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductByIdResponse {
    private String productName;
    private long productId;
    private long quantity;
    private long price;


}
