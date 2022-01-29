package com.meli.loan.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

/**
 * Contains the data to paginate.
 */
@Data
@SuperBuilder
public class Pagination {

    /**
     * Page to retrieve data.
     */
    private int page;

    /**
     * amount of data to retrieve per page.
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
