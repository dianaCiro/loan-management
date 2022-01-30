package com.meli.loan.model;

import com.meli.loan.enums.PaymentType;
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
    private PaymentType paymentType;

    /**
     * Payment date.
     */
    private LocalDateTime date;

    /**
     * Payment amount.
     */
    private double amount;
}
