package com.meli.loan.service;

import com.meli.loan.exception.NotFoundException;
import com.meli.loan.model.CreatePaymentParams;
import com.meli.loan.model.Loan;
import com.meli.loan.model.Payment;
import com.meli.loan.repository.LoanRepository;
import com.meli.loan.repository.PaymentRepository;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.Optional;

/**
 * Use case to create a payment.
 */
public class PaymentCreateService {

    /**
     * PaymentRepository dependency.
     */
    private PaymentRepository paymentRepository;

    /**
     * LoanRepository dependency.
     */
    private LoanRepository loanRepository;

    /**
     * PaymentCreateService constructor.
     * @param paymentRepository
     * @param loanRepository
     */
    public PaymentCreateService(PaymentRepository paymentRepository, LoanRepository loanRepository) {
        this.paymentRepository = paymentRepository;
        this.loanRepository = loanRepository;
    }

    /**
     * Create a payment.
     * @param createPaymentParams data to create a payment.
     * @param loanId loan identifier.
     */
    @Transactional
    public Payment create(CreatePaymentParams createPaymentParams, String loanId) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);

        if(optionalLoan.isPresent()) {
            Payment payment = new Payment(
                    createPaymentParams.getPaymentType(),
                    createPaymentParams.getDate(),
                    createPaymentParams.getAmount(),
                    loanId
            );
            return paymentRepository.create(payment);
        } else {
            throw new NotFoundException(MessageFormat.format("The entered loan {0} does not exist", loanId));
        }
    }
}
