package com.meli.loan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Contains the data to create a payment.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentParams {

    /**
     * Payment type.
     */
    private String paymentType;

    /**
     * Payment date.
     */
    private LocalDateTime date;

    /**
     * Payment amount.
     */
    private double amount;
}
