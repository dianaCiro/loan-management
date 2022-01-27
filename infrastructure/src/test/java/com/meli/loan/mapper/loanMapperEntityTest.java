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

    @Mock
    private ModelMapper modelMapper;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        loanTestDataBuilder = new LoanTestDataBuilder();
        loanEntityTestDataBuilder = new LoanEntityTestDataBuilder();
    }

    @Test
    public void convertEntityToDomainObject(){
        LoanEntity loanEntity = loanEntityTestDataBuilder.build();
        Loan loan = loanTestDataBuilder.build();

        Mockito.when(modelMapper.map(loanEntity, Loan.class)).thenReturn(loan);

        Loan loanResponse = loanMapperEntity.convertEntityToDomainObject(loanEntity);

        assertEquals(loan, loanResponse);
    }

    @Test
    public void convertDomainToEntityObject(){
        LoanEntity loanEntity = loanEntityTestDataBuilder.build();
        Loan loan = loanTestDataBuilder.build();

        Mockito.when(modelMapper.map(loan, LoanEntity.class)).thenReturn(loanEntity);

        LoanEntity loanEntityResponse = loanMapperEntity.convertDomainToEntityObject(loan);

        assertEquals(loanEntity, loanEntityResponse);
    }
}
