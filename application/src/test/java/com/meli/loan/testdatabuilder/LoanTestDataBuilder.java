package com.meli.loan.testdatabuilder;

import java.time.LocalDateTime;

import com.meli.loan.model.Loan;

public class LoanTestDataBuilder {

	private double amount;
	private int term;
	private double rate;
	private LocalDateTime date;
	
	public LoanTestDataBuilder() {
		this.amount = 1000.0;
		this.term = 12;
		this.rate = 0.05;
		this.date = LocalDateTime.now();
	}
	
	public Loan build() {
		return new Loan(amount, rate, term, date);
	}
}
