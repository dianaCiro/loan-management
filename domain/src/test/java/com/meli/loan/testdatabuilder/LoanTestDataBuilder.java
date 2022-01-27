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

	public LoanTestDataBuilder setAmount(double amount) {
		this.amount = amount;
		return this;
	}

	public LoanTestDataBuilder setTerm(int term) {
		this.term = term;
		return this;
	}

	public LoanTestDataBuilder setRate(double rate) {
		this.rate = rate;
		return this;
	}

	public LoanTestDataBuilder setDate(LocalDateTime date) {
		this.date = date;
		return this;
	}

	public Loan build() {
		return new Loan(amount, rate, term, date);
	}
}
