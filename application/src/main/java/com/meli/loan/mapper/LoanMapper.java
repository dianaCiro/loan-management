package com.meli.loan.mapper;

import org.springframework.stereotype.Component;

import com.meli.loan.model.Loan;
import com.meli.loan.response.LoanResource;

/**
 * This class transforms a loan to a LoanResorce.
 */
@Component
public class LoanMapper {

	/**
	 * Convert a loan object to LoanResource.
	 * 
	 * @param loan to convert.
	 * @return the LoanResource instance.
	 */
	public LoanResource convertLoanToLoanResource(Loan loan) {
		return LoanResource.builder()
				.loanId(loan.getId())
				.installment(loan.calculateMontlyAmount()).build();
	}
}
