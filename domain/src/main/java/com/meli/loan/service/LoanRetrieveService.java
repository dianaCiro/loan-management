package com.meli.loan.service;

import com.meli.loan.model.LoanFilter;
import com.meli.loan.model.PagedLoan;
import com.meli.loan.repository.LoanRepository;

/**
 * Use case to retrieve a loan application.
 */
public class LoanRetrieveService {

    /**
     * LoanRepository dependency.
     */
    private LoanRepository loanRepository;

    /**
     * The LoanRetrieveService constructor.
     * @param loanRepository
     */
    public LoanRetrieveService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    /**
     * Retrieves loans with pagination.
     * @param loanFilter instance.
     * @return PagedLoan instance.
     */
    public PagedLoan retrieveLoans(LoanFilter loanFilter) {
        return loanRepository.retrieveLoans(loanFilter);
    }
}
