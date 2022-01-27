package com.meli.loan.testdatabuilder;

import com.meli.loan.entity.LoanEntity;
import com.meli.loan.model.Loan;

import java.time.LocalDateTime;

public class LoanEntityTestDataBuilder {

	private String id;
	private double amount;
	private int term;
	private double rate;
	private LocalDateTime date;

	public LoanEntityTestDataBuilder() {
		this.amount = 1000.0;
		this.term = 12;
		this.rate = 0.05;
		this.date = LocalDateTime.now();
		this.id = "4597fb9b-d954-4593-9e3e-3ea78685b32b";
	}

	public LoanEntity build() {
		return new LoanEntity(id, amount, term, rate, date);
	}
}
