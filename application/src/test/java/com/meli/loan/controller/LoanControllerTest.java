package com.meli.loan.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private JpaLoanRepository jpaLoanRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private CreateLoanParamsTestDataBuilder createLoanParamsBuilder;

	@Before
	public void setUp() {
		createLoanParamsBuilder = new CreateLoanParamsTestDataBuilder();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void createLoanApplication() throws JsonProcessingException, Exception {
		CreateLoanParams createLoanParams = createLoanParamsBuilder.build();

		ResultActions result = mockMvc.perform(post("/loan").contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(createLoanParams)));

		result.andExpect(status().isOk());

		LoanResource loanResource = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				LoanResource.class);

		Optional<LoanEntity> optionalLoan = jpaLoanRepository.findById(loanResource.getLoanId());

		assertTrue(optionalLoan.isPresent());
	}
}
