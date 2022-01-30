package com.meli.loan.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.meli.loan.jpa.JpaPaymentRepository;
import com.meli.loan.testdatabuilder.CreatePaymentParamsTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.loan.controller.LoanController;
import com.meli.loan.jpa.JpaLoanRepository;
import com.meli.loan.model.CreateLoanParams;
import com.meli.loan.response.ResponseError;
import com.meli.loan.testdatabuilder.CreateLoanParamsTestDataBuilder;

import javax.persistence.EntityManager;
import java.text.MessageFormat;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanExceptionHandlerTest {

	@MockBean
	private JpaLoanRepository jpaLoanRepository;

	@MockBean
	private JpaPaymentRepository jpaPaymentRepository;

	@MockBean
	private EntityManager entityManager;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private CreateLoanParamsTestDataBuilder createLoanParamsTestDataBuilder;
	
	@Before
	public void setUp() {
		createLoanParamsTestDataBuilder = new CreateLoanParamsTestDataBuilder();
	}
	
	@Test
	public void manageBusinessExceptionHandler() throws Exception {
		 CreateLoanParams createLoanParams = createLoanParamsTestDataBuilder.setRate(-1.0).build();
			
		 ResultActions result = mockMvc.perform(
			 post("/loan")
			 .contentType(MediaType.APPLICATION_JSON)
			 .content(this.objectMapper.writeValueAsString(createLoanParams))
		 );
		 
		 result.andExpect(status().isUnprocessableEntity());
		 
		 ResponseError error = this.objectMapper.readValue(
			 result.andReturn().getResponse().getContentAsString(), ResponseError.class);
		 
		 assertEquals("The rate must not be less than 0", error.getMessage());
		 
	} 
	
	@Test
	public void manageGeneralException() throws Exception {
		 CreateLoanParams createLoanParams = createLoanParamsTestDataBuilder.build();

		 Mockito.when(jpaLoanRepository.save(Mockito.any())).thenThrow(new RuntimeException());

		 ResultActions result = mockMvc.perform(
			 post("/loan")
			 .contentType(MediaType.APPLICATION_JSON)
			 .content(this.objectMapper.writeValueAsString(createLoanParams))
		 );
		 
		 result.andExpect(status().isInternalServerError());
		 
		 ResponseError error = this.objectMapper.readValue(
			 result.andReturn().getResponse().getContentAsString(), ResponseError.class);
		 
		 assertEquals("Internal server error", error.getMessage());
		 
	}

	@Test
	public void manageConstraintViolationException() throws Exception {
		CreateLoanParams createLoanParams = createLoanParamsTestDataBuilder.build();

		Mockito.when(jpaLoanRepository.save(Mockito.any())).thenThrow(new RuntimeException());

		String url = "/loan?page=ASDF&limit=2&sortColumn=date&sortDirection=ASC";

		ResultActions result = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnprocessableEntity());

		ResponseError error = this.objectMapper.readValue(
				result.andReturn().getResponse().getContentAsString(), ResponseError.class);

		assertEquals("Only numbers and positive numbers are allowed", error.getMessage());

	}

	@Test
	public void manageNotFoundException() throws Exception {

		String loanId = "90e4dd94-e042-4d55-8473-7f8f27ca67";
		String url = MessageFormat.format("/loan/{0}/payment", loanId);
		CreatePaymentParamsTestDataBuilder createPaymentParamsTestDataBuilder = new CreatePaymentParamsTestDataBuilder();

		ResultActions result = mockMvc.perform(
				post(url)
						.contentType(MediaType.APPLICATION_JSON)
						.content(this.objectMapper.writeValueAsString(createPaymentParamsTestDataBuilder.build()))
		);

		result.andExpect(status().isNotFound());

		ResponseError error = this.objectMapper.readValue(
				result.andReturn().getResponse().getContentAsString(), ResponseError.class);

		assertEquals(MessageFormat.format("The entered loan {0} does not exist", loanId), error.getMessage());

	}
}
