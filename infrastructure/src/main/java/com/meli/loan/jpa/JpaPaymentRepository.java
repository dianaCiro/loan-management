package com.meli.loan.jpa;

import com.meli.loan.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The JpaPaymentRepository interface.
 */
public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
