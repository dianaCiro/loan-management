package com.meli.loan.repository;

import com.meli.loan.model.Loan;
import com.meli.loan.model.LoanFilter;
import com.meli.loan.model.PagedLoan;

/**
 * LoanRepository interface.
 * @author diana.ciro
 *
 */
public interface LoanRepository {

	/**
	 * Persists the loan object in database.
	 * @param loan to save.
	 */
	public void create(Loan loan);

	/**
	 * Retrieves loans with pagination.
	 * @param loanFilter instance.
	 * @return PagedLoan instance.
	 */
    PagedLoan retrieveLoans(LoanFilter loanFilter);
}
