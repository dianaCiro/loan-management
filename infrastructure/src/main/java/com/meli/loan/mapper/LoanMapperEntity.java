package com.meli.loan.mapper;

import org.springframework.stereotype.Component;

import com.meli.loan.entity.LoanEntity;
import com.meli.loan.model.Loan;

/**
 * Mapper to convert loan entity and domain objects.
 */
@Component
public class LoanMapperEntity {

	/**
	 * Convert entity to domain object.
	 * @param loanEntity
	 * @return
	 */
	public Loan convertEntityToDomainObject(LoanEntity loanEntity) {
		return new Loan(loanEntity.getId(), loanEntity.getAmount(), loanEntity.getRate(), loanEntity.getTerm(), loanEntity.getDate());
	}
	
	/**
	 * Convert domain to entity object.
	 * @param loan
	 * @return
	 */
	public LoanEntity convertDomainToEntityObject(Loan loan) {
		return new LoanEntity(loan.getId(), loan.getAmount(), loan.getTerm(), loan.getRate(), loan.getDate());
	}
}
