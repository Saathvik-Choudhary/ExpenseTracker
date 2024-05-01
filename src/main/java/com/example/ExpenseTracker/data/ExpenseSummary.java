package com.example.ExpenseTracker.data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class to store the copy of the entity so the copy of the entity
 */

public class ExpenseSummary {

    private final BigDecimal cost;

    private final String title;

    private final Date dateOfExpense;

    public ExpenseSummary( final String title,
                           final BigDecimal cost,
                           final Date dateOfExpense) {
        this.cost = cost;
        this.title = title;
        this.dateOfExpense = dateOfExpense;
    }

    /**
     * Get the cost of the expense
     *
     * @return the cost of the expense
     */
    public BigDecimal getCost() {
        return cost;
    }


    /**
     * Gets the date  of the expense
     *
     * @return the date of the expense
     */
    public Date getDateOfExpense() {
        return dateOfExpense;
    }

    /**
     * Get the title of the expense
     *
     * @return the title of the expensse
     */
    public String getTitle() {
        return title;
    }
}
