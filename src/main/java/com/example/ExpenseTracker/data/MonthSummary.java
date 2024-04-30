package com.example.ExpenseTracker.data;

import java.math.BigDecimal;
import java.util.Date;

public class MonthSummary {

    private final Date month;

    private final BigDecimal totalExpenses;

    public MonthSummary(Date month, BigDecimal totalExpenses) {
        this.month = month;
        this.totalExpenses = totalExpenses;
    }

    public Date getMonth() {
        return month;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }
}
