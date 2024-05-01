package com.example.ExpenseTracker.data;

import com.example.ExpenseTracker.common.Request;

import java.util.Date;

/**
 * Class to store the request to get the month wise summary of the expenses
 *
 * The request contains the number of months summary and the date from which the months summaries are needed
 */
public class GetAllExpenseSummaryRequest extends Request {

    private final Date requestDate;

    private final int numberOfMonths;

    public GetAllExpenseSummaryRequest(Date requestDate, int numberOfMonths) {
        this.requestDate = requestDate;
        this.numberOfMonths = numberOfMonths;
    }

    /**
     * Get the date from which the expense summary was requested
     *
     * @return  the date from which the expense summary was requested
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * Get the number of months of summary that was requested
     *
     * @return  the number of months of summary that was requested
     */
    public int getNumberOfMonths() {
        return numberOfMonths;
    }
}
