package com.example.ExpenseTracker.data;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  Class used as a custom data type to store the month and the total expenses of that month
 */
public class MonthSummary {

    private final Date month;

    private final BigDecimal totalExpenses;

    public MonthSummary(Date month, BigDecimal totalExpenses) {
        this.month = month;
        this.totalExpenses = totalExpenses;
    }

    /**
     * Get the month for which the expense is being stored
     *
     * @return  the month for which the expense is being stored
     */
    public Date getMonth() {
        return month;
    }

    /**
     * Get the total expenses of the specific month
     *
     * @return  the total expenses of the specific month
     */
    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }
}
