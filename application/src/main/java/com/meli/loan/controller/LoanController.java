package com.meli.loan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.loan.mapper.LoanMapper;
import com.meli.loan.model.CreateLoanParams;
import com.meli.loan.response.LoanResource;
import com.meli.loan.service.LoanCreateService;

/**
 * Endpoints to handle Loan requests.
 * @author diana.ciro
 *
 */
@RestController
@RequestMapping("/loan")
public class LoanController {
	
	/**
	 * LoanService dependency.
	 */
	private LoanCreateService loanCreateService;
	
	/**
	 * LoanMapper dependency.
	 */
	private LoanMapper loanMapper;
	

	/**
	 * The loanController constructor.
	 * @param loanCreateService
	 * @param loanMapper
	 */
	public LoanController(LoanCreateService loanCreateService, LoanMapper loanMapper) {
		this.loanCreateService = loanCreateService;
		this.loanMapper = loanMapper;
	}

	/**
	 * Endpoint to create a Loan application.
	 * @param createLoanParams contains data to create a loan.
	 * @return ResponseEntity<LoanResource> instance.
	 */
	@PostMapping
	public ResponseEntity<LoanResource> createLoanApplication(@RequestBody CreateLoanParams createLoanParams) {
		return ResponseEntity.ok(loanMapper.convertLoanToCreateLoanResponse(
				loanCreateService.create(createLoanParams)));
	}
}
