package com.meli.loan.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class to represent a loan resource.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanResource {

	/**
	 * Unique loan identifier.
	 */
	@JsonProperty("loan_id")
	private String loanId;
	
	/**
	 * Monthly loan payment.
	 */
	private double installment;
}
