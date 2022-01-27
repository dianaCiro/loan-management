package com.meli.loan.service;

import com.meli.loan.model.CreateLoanParams;
import com.meli.loan.model.Loan;
import com.meli.loan.repository.LoanRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Use case to create a loan application.
 * @author diana.ciro
 *
 */
public class LoanCreateService {
	
	/**
	 * LoanRepository dependency.
	 */
	private LoanRepository loanRepository;
	
	/**
	 * The loanService constructor.
	 * @param loanRepository
	 */
	public LoanCreateService(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}

	/**
	 * Create loan application.
	 * @param createLoanParams
	 * @return the loan created.
	 */
	@Transactional
	public Loan create(CreateLoanParams createLoanParams) {
		Loan loan = new Loan(
				createLoanParams.getAmount(),
				createLoanParams.getRate(),
				createLoanParams.getTerm(),
				createLoanParams.getDate()
		);
		loanRepository.create(loan);
		return loan;
	}
}
