package com.example.ExpenseTracker.data;

import com.example.ExpenseTracker.common.Request;
import com.example.ExpenseTracker.core.InputCurrency;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class which stores the request to save an expense
 */
public class SaveExpenseRequest extends Request {

    private final BigDecimal cost;

    private final String title;

    private final Date dateOfExpense;

    private final InputCurrency currency;

    public SaveExpenseRequest( final String title,
                                final BigDecimal cost,
                                final Date dateOfExpense,
                                final InputCurrency currency) {
        this.cost = cost;
        this.title = title;
        this.dateOfExpense = dateOfExpense;
        this.currency=currency;
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
     * @return the title of the expense
     */
    public String getTitle() {
        return title;
    }

    public InputCurrency getCurrency() {
        return currency;
    }
}
