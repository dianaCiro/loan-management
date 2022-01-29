package com.meli.loan.mapper;

import com.meli.loan.entity.LoanEntity;
import com.meli.loan.model.Loan;
import com.meli.loan.testdatabuilder.LoanEntityTestDataBuilder;
import com.meli.loan.testdatabuilder.LoanTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;

public class loanMapperEntityTest {

    private LoanEntityTestDataBuilder loanEntityTestDataBuilder;
    private LoanTestDataBuilder loanTestDataBuilder;

    @InjectMocks
    private LoanMapperEntity loanMapperEntity;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        loanTestDataBuilder = new LoanTestDataBuilder();
        loanEntityTestDataBuilder = new LoanEntityTestDataBuilder();
    }

    @Test
    public void convertEntityToDomainObject(){
        LoanEntity loanEntity = loanEntityTestDataBuilder.build();
        Loan loanResponse = loanMapperEntity.convertEntityToDomainObject(loanEntity);

        assertEquals(loanEntity.getId(), loanResponse.getId());
        assertEquals(loanEntity.getAmount(), loanResponse.getAmount(), 0.0);
        assertEquals(loanEntity.getTerm(), loanResponse.getTerm());
        assertEquals(loanEntity.getRate(), loanResponse.getRate(), 0.0);
        assertEquals(loanEntity.getTerm(), loanResponse.getTerm());
    }

    @Test
    public void convertDomainToEntityObject(){
        Loan loan = loanTestDataBuilder.build();

        LoanEntity loanEntityResponse = loanMapperEntity.convertDomainToEntityObject(loan);

        assertEquals(loan.getId(), loanEntityResponse.getId());
        assertEquals(loan.getAmount(), loanEntityResponse.getAmount(), 0.0);
        assertEquals(loan.getTerm(), loanEntityResponse.getTerm());
        assertEquals(loan.getRate(), loanEntityResponse.getRate(), 0.0);
        assertEquals(loan.getTerm(), loanEntityResponse.getTerm());
    }
}
