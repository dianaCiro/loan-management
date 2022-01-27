package com.meli.loan.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.meli.loan.exception.BusinessException;
import com.meli.loan.testdatabuilder.LoanTestDataBuilder;

public class LoanTest {

	private static final String AMONT_EXCEPTION_MESSAGE = "The loan amount must be greater than 0";
	private static final String TERM_EXCEPTION_MESSAGE = "The number of months it will take until the loan is paid off, must be greater than 0.";
	private static final String RATE_EXCEPTION_MESSAGE = "The rate must not be less than 0";
	private static final String DATE_EXCEPTION_MESSAGE = "You must enter the date when you applied for the loan";

	private LoanTestDataBuilder loanTestDataBuilder;

	@Before
	public void setUp() {
		loanTestDataBuilder = new LoanTestDataBuilder();
	}

	@Test
	public void setAmountLessThanZero() {
		try {
			loanTestDataBuilder.setAmount(-1).build();
			fail();
		} catch (Exception e) {
			assertEquals(BusinessException.class, e.getClass());
			assertEquals(AMONT_EXCEPTION_MESSAGE, e.getMessage());
		}
	}

	@Test
	public void setTermLessThanZero() {
		try {
			loanTestDataBuilder.setTerm(-1).build();
			fail();
		} catch (Exception e) {
			assertEquals(BusinessException.class, e.getClass());
			assertEquals(TERM_EXCEPTION_MESSAGE, e.getMessage());
		}
	}

	@Test
	public void setRateLessThanZero() {
		try {
			loanTestDataBuilder.setRate(-1).build();
			fail();
		} catch (Exception e) {
			assertEquals(BusinessException.class, e.getClass());
			assertEquals(RATE_EXCEPTION_MESSAGE, e.getMessage());
		}
	}

	@Test
	public void setDateNull() {
		try {
			loanTestDataBuilder.setDate(null).build();
			fail();
		} catch (Exception e) {
			assertEquals(BusinessException.class, e.getClass());
			assertEquals(DATE_EXCEPTION_MESSAGE, e.getMessage());
		}
	}

	@Test
	public void calculateMontlyAmount() {
		Loan loan = loanTestDataBuilder.setAmount(1000).setRate(0.05).setTerm(12).build();
		assertThat(85.61, is(loan.calculateMontlyAmount()));
	}
}
