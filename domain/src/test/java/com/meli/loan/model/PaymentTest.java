package com.meli.loan.model;

import com.meli.loan.exception.BusinessException;
import com.meli.loan.testdatabuilder.PaymentTestDataBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PaymentTest {

    private PaymentTestDataBuilder paymentTestDataBuilder;

    @Before
    public void setUp(){
        paymentTestDataBuilder = new PaymentTestDataBuilder();
    }

    @Test
    public void setAmountLessThanZero() {
        try {
            paymentTestDataBuilder.setAmount(-1).build();
            fail();
        } catch (Exception e) {
            assertEquals(BusinessException.class, e.getClass());
            assertEquals(Payment.AMONT_EXCEPTION_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void setLoanId() {
        try {
            paymentTestDataBuilder.setLoanId(null).build();
            fail();
        } catch (Exception e) {
            assertEquals(BusinessException.class, e.getClass());
            assertEquals(Payment.LOAN_ID_EXCEPTION, e.getMessage());
        }
    }

    @Test
    public void setPaymentTypeNotExisting() {
        try {
            paymentTestDataBuilder.setPaymentType("pending").build();
            fail();
        } catch (Exception e) {
            assertEquals(BusinessException.class, e.getClass());
            assertEquals(Payment.PAYMENT_TYPE_INVALID, e.getMessage());
        }
    }

    @Test
    public void setPaymentTypeNull() {
        try {
            paymentTestDataBuilder.setPaymentType(null).build();
            fail();
        } catch (Exception e) {
            assertEquals(BusinessException.class, e.getClass());
            assertEquals(Payment.PAYMENT_TYPE_INVALID, e.getMessage());
        }
    }

    @Test
    public void setDateNull() {
        try {
            paymentTestDataBuilder.setDate(null).build();
            fail();
        } catch (Exception e) {
            assertEquals(BusinessException.class, e.getClass());
            assertEquals(Payment.DATE_EXCEPTION_MESSAGE, e.getMessage());
        }
    }
}
