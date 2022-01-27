package com.meli.loan.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.meli.loan.entity.LoanEntity;
import com.meli.loan.model.Loan;

/**
 * Mapper to convert entity and domain objects.
 * @author diana.ciro
 *
 */
@Component
public class loanMapperEntity {

	private ModelMapper modelMapper;
	
	public loanMapperEntity() {
		this.modelMapper = new ModelMapper();
	}

	/**
	 * Convert entity to domain object.
	 * @param loanEntity
	 * @return
	 */
	public Loan convertEntityToDomainObject(LoanEntity loanEntity) {
		return modelMapper.map(loanEntity, Loan.class);
	}
	
	/**
	 * Convert domain to entity object.
	 * @param loan
	 * @return
	 */
	public LoanEntity convertDomainToEntityObject(Loan loan) {
		return modelMapper.map(loan, LoanEntity.class);
	}
}
