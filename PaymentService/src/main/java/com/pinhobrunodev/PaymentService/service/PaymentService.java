package com.pinhobrunodev.PaymentService.service;

import com.pinhobrunodev.PaymentService.model.PaymentByIdResponse;
import com.pinhobrunodev.PaymentService.model.PaymentRequest;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentByIdResponse getPaymentDetails(long orderId);
}
