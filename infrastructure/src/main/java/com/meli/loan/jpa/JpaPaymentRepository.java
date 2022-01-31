package com.meli.loan.jpa;

import com.meli.loan.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

/**
 * The JpaPaymentRepository interface.
 */
public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long> {

    /**
     * Retrieves the total amount of payments made until the given date.
     * @param loanId loan unique identifier.
     * @param date to filter the payments made.
     * @return the payment total amount made.
     */
    @Query("SELECT SUM(p.amount) FROM payment p WHERE p.paymentType = 'made' AND p.loan.id = :loanId AND p.date <= :date")
    Double getSumPaymentsMade(@Param("loanId") String loanId, @Param("date") LocalDateTime date);
}
