package com.meli.loan.repositoryimpl;

import com.meli.loan.entity.PaymentEntity;
import com.meli.loan.jpa.JpaPaymentRepository;
import com.meli.loan.mapper.PaymentMapperEntity;
import com.meli.loan.model.Payment;
import com.meli.loan.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Is responsible for registering the payments in the database.
 */
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    /**
     * JpaPaymentRepository dependency to database access.
     */
    private JpaPaymentRepository jpaPaymentRepository;

    /**
     * PaymentMapperEntity Dependency to mapper objects.
     */
    private PaymentMapperEntity paymentMapperEntity;

    /**
     * The PaymentRepositoryImpl constructor.
     * @param jpaPaymentRepository
     * @param paymentMapperEntity
     */
    public PaymentRepositoryImpl(JpaPaymentRepository jpaPaymentRepository, PaymentMapperEntity paymentMapperEntity) {
        this.jpaPaymentRepository = jpaPaymentRepository;
        this.paymentMapperEntity = paymentMapperEntity;
    }

    /**
     * Persists the payment object in database.
     * @param payment payment to save.
     * @return the payment saved.
     */
    @Override
    public Payment create(Payment payment) {
        PaymentEntity paymentEntity = jpaPaymentRepository.save(
                paymentMapperEntity.convertDomainToEntityObject(payment)
        );
        return paymentMapperEntity.convertEntityToDomainObject(paymentEntity);
    }

    /**
     * Retrieves the total amount of payments made until the given date.
     * @param loanId loan unique identifier.
     * @param date to filter the payments made.
     * @return the payment total amount made.
     */
    @Override
    public double getSumPaymentsMade(String loanId, LocalDateTime date) {
        Double sum = jpaPaymentRepository.getSumPaymentsMade(loanId, date);
        if(Optional.ofNullable(sum).isPresent()) {
            return sum.doubleValue();
        } else {
            return 0.0;
        }
    }
}
