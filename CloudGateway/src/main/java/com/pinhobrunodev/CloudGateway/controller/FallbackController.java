package com.pinhobrunodev.CloudGateway.controller;

import com.pinhobrunodev.CloudGateway.helper.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallback() {
        return Constants.ORDER_SERVICE_FALLBACK_MSG;
    }

    @GetMapping("/productServiceFallBack")
    public String productServiceFallback() {
        return Constants.PRODUCT_SERVICE_FALLBACK_MSG;
    }

    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallback() {
        return Constants.PAYMENT_SERVICE_FALLBACK_MSG;
    }
}
