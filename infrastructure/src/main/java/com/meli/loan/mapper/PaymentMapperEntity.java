package com.meli.loan.mapper;

import com.meli.loan.entity.PaymentEntity;
import com.meli.loan.model.Payment;
import org.springframework.stereotype.Component;

/**
 * Mapper to convert payment entity and domain objects.
 */
@Component
public class PaymentMapperEntity {

    /**
     * Convert PaymentEntity to Payment object.
     * @param paymentEntity to convert.
     * @return Payment instance.
     */
    public Payment convertEntityToDomainObject(PaymentEntity paymentEntity){
        return new Payment(
                paymentEntity.getId(),
                paymentEntity.getPaymentType(),
                paymentEntity.getLoan().getId(),
                paymentEntity.getDate(),
                paymentEntity.getAmount()
        );
    }

    /**
     * Convert Payment to PaymentEntity.
     * @param payment to convert.
     * @return PaymentEntity instance.
     */
    public PaymentEntity convertDomainToEntityObject(Payment payment){
        return  new PaymentEntity(
                payment.getId(),
                payment.getLoanId(),
                payment.getPaymentType(),
                payment.getDate(),
                payment.getAmount()
        );
    }
}
