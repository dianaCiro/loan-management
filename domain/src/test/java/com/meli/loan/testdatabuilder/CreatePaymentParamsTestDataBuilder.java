package com.meli.loan.testdatabuilder;

import com.meli.loan.enums.PaymentType;
import com.meli.loan.model.CreatePaymentParams;

import java.time.LocalDateTime;

public class CreatePaymentParamsTestDataBuilder {

    private PaymentType paymentType;
    private LocalDateTime date;
    private double amount;

    public CreatePaymentParamsTestDataBuilder() {
        paymentType = PaymentType.made;
        date = LocalDateTime.now();
        amount = 500;
    }

    public CreatePaymentParams build(){
        return CreatePaymentParams.builder()
                .amount(amount)
                .date(date)
                .paymentType(paymentType)
                .build();
    }
}
