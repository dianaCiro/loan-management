package com.meli.loan.repository;

import com.meli.loan.model.Loan;

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
}
