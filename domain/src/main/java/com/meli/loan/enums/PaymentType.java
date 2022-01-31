package com.meli.loan.enums;

import com.meli.loan.model.Payment;

import java.util.Optional;

public enum PaymentType {
    made,
    missed;

    public static Optional<PaymentType> getPaymentByName(String paymentTypeName) {
        PaymentType paymentType = null;
        for (PaymentType p : values()){
            if(p.name().equals(paymentTypeName)){
                paymentType = p;
            }
        }

        return Optional.ofNullable(paymentType);
    }
}
