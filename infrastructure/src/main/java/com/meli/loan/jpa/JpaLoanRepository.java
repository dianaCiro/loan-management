package com.meli.loan.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The JpaRepository interface.
 */
public interface JpaLoanRepository extends JpaRepository<LoanEntity, String>, JpaSpecificationExecutor<LoanEntity> {

}
