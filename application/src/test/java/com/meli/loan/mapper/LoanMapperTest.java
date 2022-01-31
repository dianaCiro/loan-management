package com.meli.loan.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.meli.loan.LoanApplication;
import com.meli.loan.model.Loan;
import com.meli.loan.response.LoanResource;
import com.meli.loan.testdatabuilder.LoanTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoanApplication.class)
public class LoanMapperTest {
	
	@Autowired
	private LoanMapper loanMapper;
	
	private LoanTestDataBuilder loanTestDataBuilder;
	
	@Before
	public void setUp() {
		loanTestDataBuilder = new LoanTestDataBuilder();
	}

	@Test
	public void convertLoanToCreateLoanResponse() {
		Loan loan = loanTestDataBuilder.build();
		
		LoanResource loanResource = loanMapper.convertLoanToLoanResource(loan);
		
		assertEquals(loan.getId(), loanResource.getLoanId());
		assertEquals(loan.calculateMontlyAmount(), loanResource.getInstallment());
	}
}
