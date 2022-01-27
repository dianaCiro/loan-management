package com.meli.loan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.meli.loan.repository.LoanRepository;
import com.meli.loan.service.LoanCreateService;

/**
 * Creates instances of service classes.
 * @author diana.ciro
 *
 */
@Configuration
public class ServiceConfig {

	/**
	 * Creates an instance of loanCreateService.
	 * @return LoanCreateService instance.
	 */
	@Bean
	public LoanCreateService createLoanCreateService(LoanRepository loanRepository) {
		return new LoanCreateService(loanRepository);
	}
}
