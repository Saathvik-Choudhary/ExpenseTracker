package com.example.ExpenseTracker.data;

import com.example.ExpenseTracker.common.PaginatedResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

/**
 * Class to store the paginated response for the request to get all expenses
 */
public class GetAllExpensesResponse extends PaginatedResponse {

    private final Page<ExpenseSummary> page;
    public GetAllExpensesResponse(final Page<ExpenseSummary> page) {
        this.page=page;
    }

    @JsonProperty
    public Page<ExpenseSummary> getPage() {
        return page;
    }
}
