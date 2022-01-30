package com.meli.loan.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains the data to create a loan.
 */
@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoanParams {

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
	
}
