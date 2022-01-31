package com.meli.loan.service;

import com.meli.loan.model.CreateLoanParams;
import com.meli.loan.model.Loan;
import com.meli.loan.model.LoanFilter;
import com.meli.loan.model.PagedLoan;
import com.meli.loan.repository.LoanRepository;
import com.meli.loan.testdatabuilder.CreateLoanParamsTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;

import static org.junit.Assert.assertEquals;

public class LoanCreateServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanCreateService loanCreateService;

    @Captor
    private ArgumentCaptor<Loan> loanArgumentCaptor;

    private CreateLoanParamsTestDataBuilder createLoanBuilder;

    @Before
    public void setUp() {
        createLoanBuilder = new CreateLoanParamsTestDataBuilder();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void create() {
        CreateLoanParams createLoanParams = createLoanBuilder.build();

        Loan loan = loanCreateService.create(createLoanParams);

        Mockito.verify(loanRepository).create(loanArgumentCaptor.capture());

        assertEquals(loanArgumentCaptor.getValue(), loan);
    }
}
