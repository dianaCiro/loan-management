package com.meli.loan.config;

import com.meli.loan.repository.PaymentRepository;
import com.meli.loan.service.LoanRetrieveService;
import com.meli.loan.service.PaymentCreateService;
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
	 * @param loanRepository dependency.
	 * @return LoanCreateService instance.
	 */
	@Bean
	public LoanCreateService createLoanCreateService(LoanRepository loanRepository) {
		return new LoanCreateService(loanRepository);
	}

	/**
	 * Creates an instance of LoanRetrieveService.
	 * @param loanRepository dependency.
	 * @return LoanRetrieveService instance.
	 */
	@Bean
	public LoanRetrieveService createLoanRetrieveService(LoanRepository loanRepository) {
		return new LoanRetrieveService(loanRepository);
	}

	/**
	 * Creates an instance of PaymentCreateService.
	 * @param paymentRepository dependency.
	 * @param loanRepository dependency.
	 * @return PaymentCreateService instance.
	 */
	@Bean
	public PaymentCreateService createPaymentCreateService(PaymentRepository paymentRepository, LoanRepository loanRepository){
		return new PaymentCreateService(paymentRepository, loanRepository);
	}
}
