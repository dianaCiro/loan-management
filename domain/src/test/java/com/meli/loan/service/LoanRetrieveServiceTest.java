package com.meli.loan.service;

import com.meli.loan.model.LoanFilter;
import com.meli.loan.repository.LoanRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LoanRetrieveServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanRetrieveService loanRetrieveService;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void retrieveLoans() {
        LoanFilter loanFilter = LoanFilter.builder().build();

        loanRetrieveService.retrieveLoans(loanFilter);

        Mockito.verify(loanRepository, Mockito.times(1)).retrieveLoans(loanFilter);

    }
}
