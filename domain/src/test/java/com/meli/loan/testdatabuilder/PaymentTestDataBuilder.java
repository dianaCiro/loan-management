package com.meli.loan.testdatabuilder;

import com.meli.loan.enums.PaymentType;
import com.meli.loan.model.Payment;

import java.time.LocalDateTime;

public class PaymentTestDataBuilder {

    private String paymentType;
    private LocalDateTime date;
    private double amount;
    private String loanId;

    public PaymentTestDataBuilder() {
        paymentType = PaymentType.made.name();
        date = LocalDateTime.now();
        amount = 500;
        loanId = "90e4dd94-e042-4d55-8473-7f8f27ca67";
    }

    public PaymentTestDataBuilder setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public PaymentTestDataBuilder setLoanId(String loanId) {
        this.loanId = loanId;
        return this;
    }

    public PaymentTestDataBuilder setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public PaymentTestDataBuilder setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public Payment build(){
        return new Payment(paymentType, date, amount, loanId);
    }
}
