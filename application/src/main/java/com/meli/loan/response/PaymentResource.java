package com.meli.loan.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Class to represent a payment resource.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResource {

    /**
     * The payment unique identifier.
     */
    private Long id;

    /**
     * The loan id unique identifier.
     */
    private String loanId;

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
