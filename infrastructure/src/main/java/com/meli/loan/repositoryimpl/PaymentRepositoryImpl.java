package com.meli.loan.repositoryimpl;

import com.meli.loan.entity.PaymentEntity;
import com.meli.loan.jpa.JpaPaymentRepository;
import com.meli.loan.mapper.PaymentMapperEntity;
import com.meli.loan.model.Payment;
import com.meli.loan.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

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
}
