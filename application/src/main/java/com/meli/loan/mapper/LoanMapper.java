package com.meli.loan.mapper;

import org.springframework.stereotype.Component;

import com.meli.loan.model.Loan;
import com.meli.loan.response.LoanResource;

/**
 * This class transforms an object to another.
 * 
 * @author diana.ciro
 *
 */
@Component
public class LoanMapper {

	/**
	 * Convert a loan object to createLoanResponse.
	 * 
	 * @param loan to convert.
	 * @return the createLoanResponse.
	 */
	public LoanResource convertLoanToCreateLoanResponse(Loan loan) {
		return LoanResource.builder().loanId(loan.getId()).installment(loan.calculateMontlyAmount()).build();
	}
}
