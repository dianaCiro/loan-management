package com.meli.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.loan.LoanApplication;
import com.meli.loan.response.DebtResource;
import com.meli.loan.response.ResponseError;
import com.meli.loan.service.DebtService;
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

import java.text.MessageFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoanApplication.class)
@Transactional
public class DebtControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void checkDebt() throws  Exception {
        String loanId = "90e4dd94-e042-4d55-8473-7f8f27ca82a1";
        String url = MessageFormat.format("/loan/{0}/debt?date=2020-01-01 14:05Z", loanId);

        ResultActions result = mockMvc.perform(
                get(url)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

        DebtResource debtResource = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
                DebtResource.class);

        assertEquals(1754.52, debtResource.getBalance(), 0.0);
    }

    @Test
    public void checkDebtDateLessThanLoanCreatedDate() throws  Exception {
        String loanId = "90e4dd94-e042-4d55-8473-7f8f27ca82a1";
        String url = MessageFormat.format("/loan/{0}/debt?date=2016-01-01 14:05Z", loanId);

        ResultActions result = mockMvc.perform(
                get(url)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());

        ResponseError responseError = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
                ResponseError.class);

        assertTrue(responseError.getMessage().contains("the date to calculate the debt is less."));
    }

    @Test
    public void checkDebtLoanIdNotExisting() throws  Exception {
        String loanId = "90e4dd94-e042-4d55-8473-7f8f27ca82a";
        String url = MessageFormat.format("/loan/{0}/debt?date=2016-01-01 14:05Z", loanId);

        ResultActions result = mockMvc.perform(
                get(url)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());

        ResponseError responseError = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
                ResponseError.class);

        assertEquals(MessageFormat.format(DebtService.LOAN_NOT_EXISTING, loanId), responseError.getMessage());
    }
}
