package com.meli.loan.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Contains the data to paginate.
 */
@Data
@SuperBuilder
public class Pagination {

    /**
     * Page to retrieve data, zero based page index(0..N).
     */
    private int page;

    /**
     * The size of the page to be returned.
     */
    private int limit;

    /**
     * name of data attribute to sort.
     */
    private String sortColumn;

    /**
     * sort direction (ASC, DESC)
     */
    private String sortDirection;
}
