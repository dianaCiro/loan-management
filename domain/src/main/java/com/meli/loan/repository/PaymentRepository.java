package com.meli.loan.repository;

import com.meli.loan.model.Payment;

/**
 * PaymentRepository interface.
 */
public interface PaymentRepository {


    /**
     * Persists the payment object in database.
     * @param payment payment to save.
     * @return the payment saved.
     */
    public Payment create(Payment payment);
}
