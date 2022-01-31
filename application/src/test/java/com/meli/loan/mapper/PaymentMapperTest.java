package com.meli.loan.mapper;

import com.meli.loan.LoanApplication;
import com.meli.loan.model.Payment;
import com.meli.loan.response.PaymentResource;
import com.meli.loan.testdatabuilder.PaymentTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoanApplication.class)
public class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;

    private PaymentTestDataBuilder paymentTestDataBuilder;

    @Before
    public void setUp(){
        paymentTestDataBuilder = new PaymentTestDataBuilder();
    }

    @Test
    public void convertPaymentToPaymentResource(){
        Payment payment = paymentTestDataBuilder.build();

        PaymentResource paymentResource = paymentMapper.convertPaymentToPaymentResource(payment);

        assertEquals(payment.getLoanId(), paymentResource.getLoanId());
        assertEquals(payment.getPaymentType(), paymentResource.getPaymentType());
        assertEquals(payment.getAmount(), paymentResource.getAmount());
        assertEquals(payment.getDate(), paymentResource.getDate());
    }
}
