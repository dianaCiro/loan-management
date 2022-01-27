package com.meli.loan.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanExceptionHandlerTest {

	@MockBean
	private JpaLoanRepository jpaLoanRepository;
	
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
	
}
