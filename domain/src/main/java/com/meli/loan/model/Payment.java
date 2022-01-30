package com.meli.loan.model;

import com.meli.loan.enums.PaymentType;
import com.meli.loan.exception.BusinessException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * This class contains the payment information.
 */
@Getter
public class Payment {

    private static final String DATE_EXCEPTION_MESSAGE = "You must enter the payment date";
    private static final String AMONT_EXCEPTION_MESSAGE = "The payment amount must be greater than 0";
    private static final String LOAN_ID_EXCEPTION = "You must enter the loan to make the payment";
    private static final String PAYMENT_TYPE_EXCEPTION = "You must enter the payment type";

    /**
     * The payment unique identifier.
     */
    private Long id;

    /**
     * Payment type.
     */
    private PaymentType paymentType;

    /**
     * Loan unique identifier.
     */
    private String loanId;

    /**
     * Payment date.
     */
    private LocalDateTime date;

    /**
     * Payment amount.
     */
    private double amount;

    /**
     * The constructor.
     * @param paymentType
     * @param date
     * @param amount
     * @param loanId
     */
    public Payment(PaymentType paymentType, LocalDateTime date, double amount, String loanId) {
        this.paymentType = paymentType;
        this.setDate(date);
        this.setAmount(amount);
        this.setLoanId(loanId);
    }

    public Payment(Long id, PaymentType paymentType, String loanId, LocalDateTime date, double amount) {
        this(paymentType, date, amount, loanId);
        this.id = id;
    }

    /**
     * validates the loanId before set the value.
     * @param loanId
     */
    private void setLoanId(String loanId) {
        if (Optional.ofNullable(loanId).isPresent()) {
            this.loanId = loanId;
        } else {
            throw new BusinessException(LOAN_ID_EXCEPTION);
        }
    }

    /**
     * validates the paymentType before set the value.
     * @param paymentType
     */
    public void setPaymentType(PaymentType paymentType) {
        if (Optional.ofNullable(paymentType).isPresent()) {
            this.paymentType = paymentType;
        } else {
            throw new BusinessException(PAYMENT_TYPE_EXCEPTION);
        }
    }

    /**
     * validates the date before set the value.
     * @param date
     */
    private void setDate(LocalDateTime date) {
        if (Optional.ofNullable(date).isPresent()) {
            this.date = date;
        } else {
            throw new BusinessException(DATE_EXCEPTION_MESSAGE);
        }
    }

    /**
     * validates the amount before set the value.
     * @param amount
     */
    private void setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        } else {
            throw new BusinessException(AMONT_EXCEPTION_MESSAGE);
        }
    }
}
