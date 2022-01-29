package com.meli.loan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Contains the retrieved loans from database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedLoan {
    /**
     * List of retrieved loans.
     */
    private List<Loan> loans;

    /**
     * Indicates the total pages.
     */
    private int totalPages;

    /**
     * Indicates the total elements.
     */
    private int totalElements;
}
