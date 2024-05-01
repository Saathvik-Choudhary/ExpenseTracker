package com.example.ExpenseTracker.data;

import com.example.ExpenseTracker.common.Response;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class to store the month wise summaries as a list of MonthSummary objects
 */
public class GetAllExpenseSummaryResponse extends Response {

    final private List<MonthSummary> monthSummaries;

    public GetAllExpenseSummaryResponse(List<MonthSummary> monthSummaries) {
        this.monthSummaries = monthSummaries;
    }

    /**
     * Get the list of Month Summary objects containing the month and total expenses in that month
     *
     * @return the list of Month Summary objects containing the month and total expenses in that month
     */
    public List<MonthSummary> getMonthSummaries() {
        return new ArrayList<>(monthSummaries); //sending copy of list not the list itself
    }
}
