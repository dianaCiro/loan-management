package com.meli.loan.repositoryimpl;

import org.springframework.stereotype.Repository;

import com.meli.loan.jpa.JpaLoanRepository;
import com.meli.loan.mapper.LoanMapperEntity;
import com.meli.loan.model.Loan;
import com.meli.loan.repository.LoanRepository;

/**
 * Is responsible for registering the loans in the database.
 *
 * @author diana.ciro
 */
@Repository
public class LoanRepositoryImpl implements LoanRepository {


    private JpaLoanRepository jpaLoanRepository;
    private LoanMapperEntity loanMapper;

    /**
     * The loanRepositoryImpl constructor.
     *
     * @param jpaLoanRepository
     */
    public LoanRepositoryImpl(JpaLoanRepository jpaLoanRepository, LoanMapperEntity loanMapper) {
        this.jpaLoanRepository = jpaLoanRepository;
        this.loanMapper = loanMapper;
    }

    /**
     * Persists the loan object in database.
     *
     * @param loan to save.
     */
    @Override
    public void create(Loan loan) {
        jpaLoanRepository.save(loanMapper.convertDomainToEntityObject(loan));
    }
}
