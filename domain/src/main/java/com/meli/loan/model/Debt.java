package com.meli.loan.model;

import lombok.Getter;

/**
 * Contains the loan debt calculated.
 */
@Getter
public class Debt {

    /**
     * The value of loan debt.
     */
    private double balance;

    /**
     * Constructor that Calculates the loan debt.
     * @param loanAmount
     * @param paymentsMade
     */
    public Debt(double loanAmount, double paymentsMade) {
        this.balance = loanAmount - paymentsMade;
    }
}
