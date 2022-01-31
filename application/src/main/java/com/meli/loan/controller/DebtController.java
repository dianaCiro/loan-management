package com.meli.loan.controller;

import com.meli.loan.config.JacksonConfiguration;
import com.meli.loan.model.Debt;
import com.meli.loan.response.DebtResource;
import com.meli.loan.service.DebtService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Endpoints to handle Loan debt.
 */
@RestController
@RequestMapping("/loan")
public class DebtController {

    /**
     * DebtService dependency.
     */
    private DebtService debtService;

    /**
     * DebtController constructor.
     * @param debtService
     */
    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }

    /**
     * Retrieve the loan debt until the specified date.
     * @param date To filter the loan payments.
     * @param loanId the loan id unique identifier.
     * @return ResponseEntity<Debt> instance.
     */
    @GetMapping("/{id}/debt")
    public ResponseEntity<DebtResource> checkDebt(@RequestParam(required = false, name = "date")
                                              @DateTimeFormat(pattern = JacksonConfiguration.FORMATO_LOCAL_DATE_TIME)
                                                      LocalDateTime date, @PathVariable("id") String loanId){
        Debt debt = debtService.checkDebt(date, loanId);
        return ResponseEntity.ok(DebtResource.builder().balance(debt.getBalance()).build());
    }
}
