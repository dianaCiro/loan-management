package com.meli.loan.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

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

	/**
	 * Loan payments.
	 */
	@OneToMany(mappedBy = "loan")
	private List<PaymentEntity> payments;

	/**
	 * The loan entity constructor.
	 * @param id
	 * @param amount
	 * @param term
	 * @param rate
	 * @param date
	 */
	public LoanEntity(String id, double amount, int term, double rate, LocalDateTime date) {
		this.id = id;
		this.amount = amount;
		this.term = term;
		this.rate = rate;
		this.date = date;
	}

	/**
	 * The constructor.
	 * @param id
	 */
	public LoanEntity(String id) {
		this.id = id;
	}
}
