package com.meli.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.loan.LoanApplication;
import com.meli.loan.entity.PaymentEntity;
import com.meli.loan.jpa.JpaPaymentRepository;
import com.meli.loan.model.CreatePaymentParams;
import com.meli.loan.response.PaymentResource;
import com.meli.loan.response.ResponseError;
import com.meli.loan.testdatabuilder.CreatePaymentParamsTestDataBuilder;
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
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoanApplication.class)
public class PaymentControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JpaPaymentRepository jpaPaymentRepository;

    private MockMvc mockMvc;
    private CreatePaymentParamsTestDataBuilder createPaymentParamsBuilder;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        this.createPaymentParamsBuilder = new CreatePaymentParamsTestDataBuilder();
    }

    @Test
    public void createPayment() throws  Exception{
        CreatePaymentParams createPaymentParams = createPaymentParamsBuilder.build();

        ResultActions result = mockMvc.perform(
                post("/loan/90e4dd94-e042-4d55-8473-7f8f27ca82a1/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(createPaymentParams)));

        result.andExpect(status().isOk());

        PaymentResource paymentResource = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
                PaymentResource.class);

        Optional<PaymentEntity> optionalPaymentEntity = jpaPaymentRepository.findById(paymentResource.getId());

        assertTrue(optionalPaymentEntity.isPresent());
        assertEquals("90e4dd94-e042-4d55-8473-7f8f27ca82a1", optionalPaymentEntity.get().getLoan().getId());
    }

    @Test
    public void createPaymentLoanNotExist() throws  Exception{
        CreatePaymentParams createPaymentParams = createPaymentParamsBuilder.build();

        ResultActions result = mockMvc.perform(
                post("/loan/90e4dd94-e042-4d55-8473-7f8f27ca67/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(createPaymentParams)));

        result.andExpect(status().isNotFound());

        ResponseError responseError = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),
                ResponseError.class);

        assertEquals("The entered loan 90e4dd94-e042-4d55-8473-7f8f27ca67 does not exist", responseError.getMessage());
    }
}
