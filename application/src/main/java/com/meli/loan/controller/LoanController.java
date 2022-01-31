package com.meli.loan.controller;

import com.meli.loan.config.JacksonConfiguration;
import com.meli.loan.model.LoanFilter;
import com.meli.loan.model.PagedLoan;
import com.meli.loan.service.LoanRetrieveService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.meli.loan.mapper.LoanMapper;
import com.meli.loan.model.CreateLoanParams;
import com.meli.loan.response.LoanResource;
import com.meli.loan.service.LoanCreateService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * Endpoints to handle Loan requests.
 */
@RestController
@RequestMapping("/loan")
@Validated
public class LoanController {

    /**
     * LoanCreateService dependency.
     */
    private LoanCreateService loanCreateService;

    /**
     *  LoanRetrieveService dependency.
     */
    private LoanRetrieveService loanRetrieveService;

    /**
     * LoanMapper dependency.
     */
    private LoanMapper loanMapper;

    private static final String NUMBER_VALIDATION = "Only numbers and positive numbers are allowed";

    /**
     * The loanController constructor.
     *
     * @param loanCreateService
     * @param loanMapper
     */
    public LoanController(LoanCreateService loanCreateService, LoanRetrieveService loanRetrieveService, LoanMapper loanMapper) {
        this.loanCreateService = loanCreateService;
        this.loanRetrieveService = loanRetrieveService;
        this.loanMapper = loanMapper;
    }

    /**
     * Endpoint to create a Loan application.
     *
     * @param createLoanParams contains data to create a loan.
     * @return ResponseEntity<LoanResource> instance.
     */
    @PostMapping
    public ResponseEntity<LoanResource> createLoanApplication(@RequestBody CreateLoanParams createLoanParams) {
        return ResponseEntity.ok(loanMapper.convertLoanToLoanResource(
                loanCreateService.create(createLoanParams)));
    }

    /**
     * Endpoint to retrieve loans with pagination.
     * @param from initial date in filter.
     * @param to final date in filter.
     * @param page number of page.
     * @param limit amount of loans to retrieve per page.
     * @param sortColumn name of loan attribute to sort.
     * @param sortDirection sort direction (ASC, DESC)
     * @return PagedLoan instance.
     */
    @GetMapping
    public ResponseEntity<PagedLoan> retrieveLoans(@RequestParam(required = false, name = "from")
                                                   @DateTimeFormat(pattern = JacksonConfiguration.FORMATO_LOCAL_DATE_TIME)
                                                           LocalDateTime from,
                                                   @RequestParam(required = false, name = "to") @DateTimeFormat(pattern = JacksonConfiguration.FORMATO_LOCAL_DATE_TIME)
                                                           LocalDateTime to,
                                                   @Valid @Pattern(regexp = "^[0-9]+$", message = NUMBER_VALIDATION)
                                                       @RequestParam(required = false, name = "page", defaultValue = "0")
                                                    String page,
                                                   @RequestParam(required = false, name = "limit", defaultValue = "5")
                                                     @Valid  @Pattern(regexp = "^[0-9]+$", message = NUMBER_VALIDATION) String limit,
                                                   @RequestParam(required = false, name = "sortColumn") String sortColumn,
                                                   @RequestParam(required = false, name = "sortDirection") String sortDirection) {
        LoanFilter loanFilter = LoanFilter.builder()
                .from(from).to(to).limit(Integer.parseInt(limit))
                .sortColumn(sortColumn).page(Integer.parseInt(page))
                .sortDirection(sortDirection).build();
        return ResponseEntity.ok(loanRetrieveService.retrieveLoans(loanFilter));
    }


}
