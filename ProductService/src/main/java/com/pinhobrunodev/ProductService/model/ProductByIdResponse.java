package com.pinhobrunodev.ProductService.model;

import com.pinhobrunodev.ProductService.entity.Product;
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


    public ProductByIdResponse(Product product) {
        productName = product.getProductName();
        productId = product.getProductId();
        quantity = product.getQuantity();
        price = product.getPrice();
    }
}
