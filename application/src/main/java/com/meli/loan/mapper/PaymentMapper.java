package com.meli.loan.mapper;

import com.meli.loan.model.Payment;
import com.meli.loan.response.PaymentResource;
import org.springframework.stereotype.Component;

/**
 * This class transforms a payment to a paymentResource.
 */
@Component
public class PaymentMapper {

    /**
     * Convert a payment object to createLoanResponse.
     *
     * @param payment to convert.
     * @return the PaymentResource instance.
     */
    public PaymentResource convertPaymentToPaymentResource(Payment payment) {
        return PaymentResource.builder()
                .id(payment.getId())
                .loanId(payment.getLoanId())
                .amount(payment.getAmount())
                .date(payment.getDate())
                .paymentType(payment.getPaymentType())
                .build();
    }
}
