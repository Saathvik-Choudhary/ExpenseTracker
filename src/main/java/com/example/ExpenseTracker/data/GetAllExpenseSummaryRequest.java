package com.example.ExpenseTracker.data;

import com.example.ExpenseTracker.common.Request;

public class GetAllExpenseSummaryRequest extends Request {

    private int numberOfMonths;

    public GetAllExpenseSummaryRequest(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public GetAllExpenseSummaryRequest(){

    }
}
