package com.meli.loan.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.loan.entity.LoanEntity;

public interface JpaLoanRepository extends JpaRepository<LoanEntity, String>{

}
