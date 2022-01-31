package com.meli.loan.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The entity payment class.
 */
@Entity(name = "payment")
@Data
@NoArgsConstructor
public class PaymentEntity {
    /**
     * Payment unique identifier.
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Indicates the loan where the payment is.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private  LoanEntity loan;

    /**
     * Payment type.
     */
    @Column(nullable = false)
    private String paymentType;

    /**
     * Payment date.
     */
    @Column(nullable = false)
    private LocalDateTime date;

    /**
     * Payment amount.
     */
    @Column(nullable = false)
    private double amount;

    /**
     * PaymentEntity constructor.
     * @param id
     * @param loanId
     * @param paymentType
     * @param date
     * @param amount
     */
    public PaymentEntity(Long id, String loanId, String paymentType, LocalDateTime date, double amount) {
        this.id = id;
        this.loan = new LoanEntity(loanId);
        this.paymentType = paymentType;
        this.date = date;
        this.amount = amount;
    }
}
