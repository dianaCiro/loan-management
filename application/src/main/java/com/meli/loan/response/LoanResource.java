package com.meli.loan.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * Class to represent a loan resource.
 * @author diana.ciro
 *
 */
@Builder
@Data
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
