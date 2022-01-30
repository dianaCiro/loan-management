package com.meli.loan.model;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.meli.loan.exception.BusinessException;

import lombok.Getter;

/**
 * This class contains the loan information.
 */
@Getter
public class Loan {
	
	private static final String AMONT_EXCEPTION_MESSAGE = "The loan amount must be greater than 0";
	private static final String TERM_EXCEPTION_MESSAGE = "The number of months it will take until the loan is paid off, must be greater than 0.";
	private static final String RATE_EXCEPTION_MESSAGE = "The rate must not be less than 0";
	private static final String DATE_EXCEPTION_MESSAGE = "You must enter the date when you applied for the loan";
	
	/**
	 * Loan unique identifier.
	 */
	private String id;
	
	/**
	 * Loan amount.
	 */
	private double amount;
	
	/**
	 * Number of months it will take until the loan is paid off.
	 */
	private int term;
	
	/**
	 * Loan rate.
	 */
	private double rate;
	
	/**
	 * Date when the loan was requested
	 */
	private LocalDateTime date;

	/**
	 * The loan constructor.
	 * @param amount
	 * @param rate
	 * @param term
	 * @param date
	 */
	public Loan(double amount, double rate, int term, LocalDateTime date) {
		this(UUID.randomUUID().toString(), amount, rate, term, date);
	}

	/**
	 * The constructor.
	 * @param id
	 * @param amount
	 * @param rate
	 * @param term
	 * @param date
	 */
	public Loan(String id, double amount, double rate, int term, LocalDateTime date){
		this.setAmount(amount);
		this.setRate(rate);
		this.setTerm(term);
		this.setDate(date);
		this.id = id;
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

	/**
	 * validates the term before set the value.
	 * @param term
	 */
	private void setTerm(int term) {
		if (term > 0) {
			this.term = term;
		} else {
			throw new BusinessException(TERM_EXCEPTION_MESSAGE);
		}
	}

	/**
	 * validates the rate before set the value.
	 * @param rate
	 */
	private void setRate(double rate) {
		if (rate >= 0) {
			this.rate = rate;
		} else {
			throw new BusinessException(RATE_EXCEPTION_MESSAGE);
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
	 * calculates the loan monthly amount.
	 * 
	 * @return the monthly amount value.
	 */
	public double calculateMontlyAmount() {
		double monthlyRate = this.rate / 12;
		double installment = (monthlyRate + monthlyRate / ( Math.pow((1+ monthlyRate) , this.term) - 1)) * this.amount;
		return Math.round(installment*100)/100.0;
	}

	/**
	 * constructor used to build object in mapper.
	 */
	private Loan(){}
}
