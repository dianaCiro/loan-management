package com.meli.loan.repository;

import com.meli.loan.model.Payment;

import java.time.LocalDateTime;

/**
 * PaymentRepository interface.
 */
public interface PaymentRepository {


    /**
     * Persists the payment object in database.
     * @param payment payment to save.
     * @return the payment saved.
     */
    Payment create(Payment payment);

    /**
     * Retrieves the total amount of payments made until the given date.
     * @param loanId loan unique identifier.
     * @param date to filter the payments made.
     * @return the payment total amount made.
     */
    double getSumPaymentsMade(String loanId, LocalDateTime date);
}
