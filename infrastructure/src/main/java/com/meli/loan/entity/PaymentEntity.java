package com.meli.loan.entity;

import com.meli.loan.enums.PaymentType;
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
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    /**
     * Payment date.
     */
    @Column
    private LocalDateTime date;

    /**
     * Payment amount.
     */
    @Column
    private double amount;

    /**
     * PaymentEntity constructor.
     * @param id
     * @param loanId
     * @param paymentType
     * @param date
     * @param amount
     */
    public PaymentEntity(Long id, String loanId, PaymentType paymentType, LocalDateTime date, double amount) {
        this.id = id;
        this.loan = new LoanEntity(loanId);
        this.paymentType = paymentType;
        this.date = date;
        this.amount = amount;
    }
}
