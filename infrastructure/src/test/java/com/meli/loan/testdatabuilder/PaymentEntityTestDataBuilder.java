package com.meli.loan.testdatabuilder;

import com.meli.loan.entity.LoanEntity;
import com.meli.loan.entity.PaymentEntity;
import com.meli.loan.enums.PaymentType;

import java.time.LocalDateTime;

public class PaymentEntityTestDataBuilder {

    private Long id;
    private LoanEntity loan;
    private String paymentType;
    private LocalDateTime date;
    private double amount;

    private LoanEntityTestDataBuilder loanEntityTestDataBuilder;

    public PaymentEntityTestDataBuilder() {
        loanEntityTestDataBuilder = new LoanEntityTestDataBuilder();
        id = 1l;
        loan = loanEntityTestDataBuilder.build();
        paymentType = PaymentType.made.name();
        date = LocalDateTime.now();
        amount = 500;
    }

    public PaymentEntity build() {
        return new PaymentEntity(
                id,
                loan.getId(),
                paymentType,
                date,
                amount
        );
    }
}
