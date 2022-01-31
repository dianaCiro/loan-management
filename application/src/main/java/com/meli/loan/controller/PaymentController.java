package com.meli.loan.controller;

import com.meli.loan.mapper.PaymentMapper;
import com.meli.loan.model.CreatePaymentParams;
import com.meli.loan.model.Payment;
import com.meli.loan.response.PaymentResource;
import com.meli.loan.service.PaymentCreateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints to handle Loan payments.
 */
@RestController
@RequestMapping("/loan")
public class PaymentController {

    /**
     * PaymentCreateService dependency.
     */
    private PaymentCreateService paymentCreateService;

    /**
     * PaymentMapper dependency.
     */
    private PaymentMapper paymentMapper;

    /**
     * The PaymentController constructor.
     * @param paymentCreateService
     */
    public PaymentController(PaymentCreateService paymentCreateService, PaymentMapper paymentMapper) {
        this.paymentCreateService = paymentCreateService;
        this.paymentMapper = paymentMapper;
    }

    /**
     * Endpoint to create a payment.
     * @param createPaymentParams contains data to create a payment.
     * @param loanId loan unique identifier.
     * @return ResponseEntity<PaymentResource> instance.
     */
    @PostMapping("/{id}/payment")
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentParams createPaymentParams,
                                                         @PathVariable("id") String loanId){
        Payment payment = paymentCreateService.create(createPaymentParams, loanId);
        return ResponseEntity.ok(paymentMapper.convertPaymentToPaymentResource(payment));
    }
}
