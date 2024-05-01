package com.example.ExpenseTracker.data;

import com.example.ExpenseTracker.common.Response;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * Class to store the save expense response and any errors that have to be shown to the user
 */
public class SaveExpenseResponse extends Response {// The response abstract class has the errors part in it

    private final BigDecimal cost;

    private final String title;

    private final Date dateOfExpense;

    private final Currency currency;

    public SaveExpenseResponse( final String title,
                               final BigDecimal cost,
                               final Date dateOfExpense,
                               final Currency currency) {
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

    public Currency getCurrency() {
        return currency;
    }
}
