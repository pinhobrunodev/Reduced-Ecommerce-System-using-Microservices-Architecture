package com.pinhobrunodev.PaymentService.repository;

import com.pinhobrunodev.PaymentService.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails,Long> {
    Optional<TransactionDetails> findByOrderId(long orderId);
}
