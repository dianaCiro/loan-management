package com.meli.loan.testdatabuilder;

import java.time.LocalDateTime;

import com.meli.loan.model.CreateLoanParams;

public class CreateLoanParamsTestDataBuilder {

	private double amount;
	private int term;
	private double rate;
	private LocalDateTime date;
	
	public CreateLoanParamsTestDataBuilder() {
		this.amount = 1000.0;
		this.term = 12;
		this.rate = 0.05;
		this.date = LocalDateTime.now();
	}
	
	public CreateLoanParamsTestDataBuilder setRate(double rate) {
		this.rate = rate;
		return this;
	}
	
	public CreateLoanParams build() {
		return CreateLoanParams.builder()
				.amount(amount)
				.date(date)
				.term(term)
				.rate(rate)
				.build();
	}
}
