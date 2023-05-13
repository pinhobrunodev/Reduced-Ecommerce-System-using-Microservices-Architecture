package com.pinhobrunodev.PaymentService.service;

import com.pinhobrunodev.PaymentService.entity.TransactionDetails;
import com.pinhobrunodev.PaymentService.helper.Constants;
import com.pinhobrunodev.PaymentService.model.PaymentRequest;
import com.pinhobrunodev.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details: {}", paymentRequest);
        TransactionDetails transactionDetails =
                TransactionDetails
                        .builder()
                        .paymentDate(Instant.now())
                        .paymentModel(paymentRequest.getPaymentMode().name())
                        .paymentStatus(Constants.SUCCESS_PAYMENT)
                        .orderId(paymentRequest.getOrderId())
                        .referenceNumber(paymentRequest.getReferenceNumber())
                        .amount(paymentRequest.getAmount())
                        .build();
        transactionDetailsRepository.save(transactionDetails);
        log.info("Transaction Completed with Id: {}", transactionDetails.getId());
        return transactionDetails.getId();
    }
}
