package com.example.ExpenseTracker.data;

import com.example.ExpenseTracker.common.Response;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetAllExpenseSummaryResponse extends Response {

    final private List<MonthSummary> monthSummaries;

    public GetAllExpenseSummaryResponse(List<MonthSummary> monthSummaries) {
        this.monthSummaries = monthSummaries;
    }

    public List<MonthSummary> getMonthSummaries() {
        return new ArrayList<>(monthSummaries);
    }
}
