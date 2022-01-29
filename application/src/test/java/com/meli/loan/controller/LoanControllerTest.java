package com.meli.loan.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.meli.loan.model.PagedLoan;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.loan.LoanApplication;
import com.meli.loan.entity.LoanEntity;
import com.meli.loan.jpa.JpaLoanRepository;
import com.meli.loan.model.CreateLoanParams;
import com.meli.loan.response.LoanResource;
import com.meli.loan.testdatabuilder.CreateLoanParamsTestDataBuilder;

@ActiveProfiles("test")
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
	public void createLoanApplication() throws  Exception {
		CreateLoanParams createLoanParams = createLoanParamsBuilder.build();

		ResultActions result = mockMvc.perform(post("/loan").contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(createLoanParams)));

		result.andExpect(status().isOk());

		LoanResource loanResource = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				LoanResource.class);

		Optional<LoanEntity> optionalLoan = jpaLoanRepository.findById(loanResource.getLoanId());

		assertTrue(optionalLoan.isPresent());
	}

	@Test
	public void retrieveLoansWithFilters() throws  Exception {

		String url = "/loan?from=2017-01-01 14:00Z&to=2020-01-01 14:05Z&page=0&limit=2&sortColumn=date&sortDirection=ASC";
		ResultActions result = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());

		PagedLoan pagedLoan = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				PagedLoan.class);

		assertEquals(2, pagedLoan.getLoans().size());
		assertEquals(4, pagedLoan.getTotalElements());
		assertEquals(2, pagedLoan.getTotalPages());
	}

	@Test
	public void retrieveLoansWithoutFilters() throws  Exception {

		String url = "/loan?page=0&limit=2&sortColumn=date&sortDirection=ASC";
		ResultActions result = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());

		PagedLoan pagedLoan = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				PagedLoan.class);

		assertEquals(2, pagedLoan.getLoans().size());
		assertEquals(5, pagedLoan.getTotalElements());
		assertEquals(3, pagedLoan.getTotalPages());
	}
	@Test
	public void retrieveLoansWithFiltersAndWithoutPagination() throws  Exception {

		String url = "/loan?from=2017-01-01 14:00Z&to=2020-01-01 14:05Z";
		ResultActions result = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());

		PagedLoan pagedLoan = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				PagedLoan.class);

		assertEquals(4, pagedLoan.getLoans().size());
		assertEquals(4, pagedLoan.getTotalElements());
		assertEquals(1, pagedLoan.getTotalPages());
	}

	@Test
	public void retrieveLoansWithoutFiltersAndWithoutPagination() throws  Exception {

		String url = "/loan";
		ResultActions result = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());

		PagedLoan pagedLoan = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
				PagedLoan.class);

		assertEquals(5, pagedLoan.getLoans().size());
		assertEquals(5, pagedLoan.getTotalElements());
		assertEquals(1, pagedLoan.getTotalPages());
	}
}
