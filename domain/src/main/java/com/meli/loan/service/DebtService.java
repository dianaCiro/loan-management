package com.meli.loan.service;

import com.meli.loan.exception.BusinessException;
import com.meli.loan.exception.NotFoundException;
import com.meli.loan.model.Debt;
import com.meli.loan.model.Loan;
import com.meli.loan.repository.LoanRepository;
import com.meli.loan.repository.PaymentRepository;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * Use case to calculate a loan debt.
 */
public class DebtService {

    /**
     * PaymentRepository dependency.
     */
    private PaymentRepository paymentRepository;

    /**
     * LoanRepository dependency.
     */
    private LoanRepository loanRepository;

    public static final String LOAN_NOT_EXISTING = "The entered loan {0} does not exist";
    public static final String DATE_EXCEPTION_MESSAGE = "The loan was created in {0} and the date to calculate the debt is less.";

    public DebtService(PaymentRepository paymentRepository, LoanRepository loanRepository) {
        this.paymentRepository = paymentRepository;
        this.loanRepository = loanRepository;
    }

    /**
     * calculate the loan debt until the specified date.
     *
     * @param date   To filter the loan payments.
     * @param loanId the loan id unique identifier.
     * @return Debt instance.
     */
    public Debt checkDebt(LocalDateTime date, String loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format(LOAN_NOT_EXISTING, loanId)));

        this.validateDebtDate(loan, date);

        return new Debt(loan.calculateTotalAmountToBePaid(),
                paymentRepository.getSumPaymentsMade(loanId, date));

    }

    /**
     * Validates debt date.
     * @param loan The loan to calculate the debt.
     * @param date To filter the loan payments.
     */
    private void validateDebtDate(Loan loan, LocalDateTime date) {
        if (loan.getDate().isAfter(date)) {
            throw new BusinessException(MessageFormat.format(DATE_EXCEPTION_MESSAGE, loan.getDate()));
        }
    }
}
