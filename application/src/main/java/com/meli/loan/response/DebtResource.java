package com.meli.loan.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class to represent a debt resource.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebtResource {

    /**
     * The value of loan debt.
     */
    private double balance;
}
