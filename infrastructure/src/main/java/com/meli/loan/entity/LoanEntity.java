package com.meli.loan.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The entity loan class.
 * 
 * @author diana.ciro
 *
 */
@Entity(name = "loan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity {
	
	/**
	 * Loan unique identifier.
	 */
	@Id
	private String id;
	
	/**
	 * Loan amount.
	 */
	@Column
	private double amount;
	
	/**
	 * Number of months it will take until the loan is paid off.
	 */
	@Column
	private int term;
	
	/**
	 * Loan rate.
	 */
	@Column
	private double rate;
	
	/**
	 * Date when the loan was requested
	 */
	@Column
	private LocalDateTime date;
}
