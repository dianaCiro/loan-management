package com.meli.loan.service;

import com.meli.loan.model.Debt;
import com.meli.loan.model.Loan;
import com.meli.loan.repository.LoanRepository;
import com.meli.loan.repository.PaymentRepository;
import com.meli.loan.testdatabuilder.LoanTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DebtServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private DebtService debtService;

    private LoanTestDataBuilder loanTestDataBuilder;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        loanTestDataBuilder = new LoanTestDataBuilder();
    }

    @Test
    public void checkDebt(){
        LocalDateTime debtDate = LocalDateTime.of(2022, Month.APRIL, 5, 14, 22);
        LocalDateTime loanDate = LocalDateTime.of(2021, Month.APRIL, 5, 14, 22);

        String loanId = "90e4dd94-e042-4d55-8473-7f8f27ca67";
        Loan loan = loanTestDataBuilder.setDate(loanDate).build();

        Mockito.when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        Mockito.when(paymentRepository.getSumPaymentsMade(loanId, debtDate)).thenReturn(300.0);

        Debt debt = debtService.checkDebt(debtDate, loanId);

        assertEquals(1754.52, debt.getBalance(), 0.0);
    }

    @Test
    public void checkDebtLoanNotExisting(){
        LocalDateTime debtDate = LocalDateTime.now();

        String loanId = "90e4dd94-e042-4d55-8473-7f8f27ca68";

        Mockito.when(loanRepository.findById(loanId)).thenReturn(Optional.ofNullable(null));

        try {
            debtService.checkDebt(debtDate, loanId);
        } catch (Exception e) {
            assertEquals(MessageFormat.format(DebtService.LOAN_NOT_EXISTING, loanId),
                    e.getMessage());
        }
        Mockito.verifyNoInteractions(paymentRepository);
    }

    @Test
    public void checkDebtDateLessThanLoanCreatedDate(){
        LocalDateTime debtDate = LocalDateTime.of(2017, Month.APRIL, 5, 14, 22);
        LocalDateTime loanDate = LocalDateTime.of(2021, Month.APRIL, 5, 14, 22);

        String loanId = "90e4dd94-e042-4d55-8473-7f8f27ca66";
        Loan loan = loanTestDataBuilder.setDate(loanDate).build();

        Mockito.when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        try {
            debtService.checkDebt(debtDate, loanId);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("the date to calculate the debt is less."));
        }
        Mockito.verifyNoInteractions(paymentRepository);
    }
}
