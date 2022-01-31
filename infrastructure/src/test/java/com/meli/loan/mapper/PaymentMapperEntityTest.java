package com.meli.loan.mapper;

import com.meli.loan.entity.PaymentEntity;
import com.meli.loan.model.Payment;
import com.meli.loan.testdatabuilder.PaymentEntityTestDataBuilder;
import com.meli.loan.testdatabuilder.PaymentTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class PaymentMapperEntityTest {

    private PaymentEntityTestDataBuilder paymentEntityTestDataBuilder;
    private PaymentTestDataBuilder paymentTestDataBuilder;

    @InjectMocks
    private PaymentMapperEntity paymentMapperEntity;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        paymentEntityTestDataBuilder = new PaymentEntityTestDataBuilder();
        paymentTestDataBuilder = new PaymentTestDataBuilder();
    }

    @Test
    public void convertEntityToDomainObject(){
        PaymentEntity paymentEntity = paymentEntityTestDataBuilder.build();

        Payment payment = paymentMapperEntity.convertEntityToDomainObject(paymentEntity);

        assertEquals(paymentEntity.getAmount(), payment.getAmount(), 0.0);
        assertEquals(paymentEntity.getPaymentType(), payment.getPaymentType());
        assertEquals(paymentEntity.getDate(), payment.getDate());
        assertEquals(paymentEntity.getLoan().getId(), payment.getLoanId());
    }

    @Test
    public void convertDomainToEntityObject(){
        Payment payment = paymentTestDataBuilder.build();

        PaymentEntity paymentEntity = paymentMapperEntity.convertDomainToEntityObject(payment);

        assertEquals(payment.getAmount(), paymentEntity.getAmount(), 0.0);
        assertEquals(payment.getPaymentType(), paymentEntity.getPaymentType());
        assertEquals(payment.getDate(), paymentEntity.getDate());
        assertEquals(payment.getLoanId(), paymentEntity.getLoan().getId());
    }

}
