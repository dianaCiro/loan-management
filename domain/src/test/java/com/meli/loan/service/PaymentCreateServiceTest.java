package com.meli.loan.service;

import com.meli.loan.exception.NotFoundException;
import com.meli.loan.model.CreatePaymentParams;
import com.meli.loan.model.Loan;
import com.meli.loan.model.Payment;
import com.meli.loan.repository.LoanRepository;
import com.meli.loan.repository.PaymentRepository;
import com.meli.loan.testdatabuilder.CreatePaymentParamsTestDataBuilder;
import com.meli.loan.testdatabuilder.LoanTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.text.MessageFormat;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PaymentCreateServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private PaymentCreateService paymentCreateService;

    @Captor
    private ArgumentCaptor<Payment> paymentCaptor;

    private CreatePaymentParamsTestDataBuilder createPaymentParamsTestDataBuilder;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        createPaymentParamsTestDataBuilder = new CreatePaymentParamsTestDataBuilder();
    }

    @Test
    public void create(){
        LoanTestDataBuilder loanTestDataBuilder = new LoanTestDataBuilder();

        Loan loan = loanTestDataBuilder.build();
        CreatePaymentParams createPaymentParams = createPaymentParamsTestDataBuilder.build();
        String loanId =  "90e4dd94-e042-4d55-8473-7f8f27ca67";

        Mockito.when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        paymentCreateService.create(createPaymentParams, loanId);

        Mockito.verify(paymentRepository).create(paymentCaptor.capture());

        assertEquals(createPaymentParams.getPaymentType(), paymentCaptor.getValue().getPaymentType());
        assertEquals(createPaymentParams.getDate(), paymentCaptor.getValue().getDate());
        assertEquals(createPaymentParams.getAmount(), paymentCaptor.getValue().getAmount(), 0.0);
        assertEquals(loanId, paymentCaptor.getValue().getLoanId());
    }

    @Test
    public void createLoanNotExist(){
        CreatePaymentParams createPaymentParams = createPaymentParamsTestDataBuilder.build();
        String loanId =  "90e4dd94-e042-4d55-8473-7f8f27ca68";

        Mockito.when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

       try {
           paymentCreateService.create(createPaymentParams, loanId);
           fail();
       } catch (Exception e) {
           assertEquals(NotFoundException.class, e.getClass());
           assertEquals(MessageFormat.format(PaymentCreateService.LOAN_NOT_EXISTING, loanId), e.getMessage());
       }

    }
}
