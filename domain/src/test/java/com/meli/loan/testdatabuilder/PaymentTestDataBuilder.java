package com.meli.loan.testdatabuilder;

import com.meli.loan.enums.PaymentType;
import com.meli.loan.model.Payment;

import java.time.LocalDateTime;

public class PaymentTestDataBuilder {

    private PaymentType paymentType;
    private LocalDateTime date;
    private double amount;
    private String loanId;

    public PaymentTestDataBuilder() {
        paymentType = PaymentType.made;
        date = LocalDateTime.now();
        amount = 500;
        loanId = "90e4dd94-e042-4d55-8473-7f8f27ca67";
    }

    public Payment build(){
        return new Payment(paymentType, date, amount, loanId);
    }
}
