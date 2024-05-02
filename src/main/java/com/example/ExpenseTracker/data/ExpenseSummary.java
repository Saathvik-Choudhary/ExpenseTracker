package com.example.ExpenseTracker.data;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * Class to store the copy of the entity so the copy of the entity
 */

public class ExpenseSummary {

    private final BigDecimal cost;

    private final Currency currency;

    private final Date dateOfExpense;

    private final String title;



    public ExpenseSummary( final String title,
                           final BigDecimal cost,
                           final Date dateOfExpense,
                           final Currency currency) {
        this.cost = cost;
        this.title = title;
        this.dateOfExpense = dateOfExpense;
        this.currency= currency;
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
     * Get teh currency of the expense
     *
     * @return the currency of the expense
     */
    public Currency getCurrency() {
        return currency;
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
     * @return the title of the expense
     */
    public String getTitle() {
        return title;
    }
}
