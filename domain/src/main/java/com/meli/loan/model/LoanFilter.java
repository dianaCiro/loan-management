package com.meli.loan.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Contains the data to filter loans.
 */
@Data
@SuperBuilder
public class LoanFilter extends Pagination{

    /**
     * Indicate the initial date to filter.
     */
    private LocalDateTime from;

    /**
     * Indicate the final date to filter.
     */
    private LocalDateTime to;
}
