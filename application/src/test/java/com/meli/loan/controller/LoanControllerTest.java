package com.meli.loan.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.meli.loan.LoanApplication;
import com.meli.loan.entity.LoanEntity;
import com.meli.loan.jpa.JpaLoanRepository;
import com.meli.loan.model.CreateLoanParams;
import com.meli.loan.response.LoanResource;
import com.meli.loan.testdatabuilder.CreateLoanParamsTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoanApplication.class)
@Transactional
public class LoanControllerTest {

	@Autowired
	private LoanController loanController;
	
	@Autowired
	private JpaLoanRepository jpaLoanRepository;
	
	private CreateLoanParamsTestDataBuilder createLoanParamsBuilder;
	
	@Before
	public void setUp() {
		createLoanParamsBuilder = new CreateLoanParamsTestDataBuilder();
	}

	@Test
	public void createLoanApplication() {
		CreateLoanParams createLoanParams = createLoanParamsBuilder.build();
		
		ResponseEntity<LoanResource> loanResource = loanController.createLoanApplication(createLoanParams);
	
		Optional<LoanEntity> optionalLoan = jpaLoanRepository.findById(loanResource.getBody().getLoanId());
		
		assertTrue(optionalLoan.isPresent());
		assertEquals(HttpStatus.OK, loanResource.getStatusCode());
	}
}
