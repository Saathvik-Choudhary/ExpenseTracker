package com.example.ExpenseTracker.core;

import com.example.ExpenseTracker.common.StringUtil;
import com.example.ExpenseTracker.data.*;
import com.example.ExpenseTracker.domain.Expense;
import com.example.ExpenseTracker.persistence.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 * Main service class containing all the business logic of the expense tracking app
 */
@Service
public class ExpenseService implements CurrencyConverter{

    @Autowired
    ExpenseRepository expenseRepository;


    /**
     * Saves the expense in the expense repository
     *
     * @param request  contains all the details of the expense
     *
     * @return any errors if any, the saved details and the saved expense message if the expense is saved successfully
     */
    public SaveExpenseResponse saveExpense(final SaveExpenseRequest request) throws IOException {

        String title = request.getTitle();
        BigDecimal cost= request.getCost();
        Date dateOfExpense= request.getDateOfExpense();

        SaveExpenseResponse response=new SaveExpenseResponse(title
                ,cost
                ,dateOfExpense
                ,request.getCurrency());

        int errorCount=0;

        if(StringUtil.isBlank(title)){
            response.addError("The title of the expense can not be null");
            errorCount++;
        }

        if(cost == null){
            response.addError("The cost of the expense can not be null");
            errorCount++;
        } else if (cost.compareTo(BigDecimal.ZERO)<0) {
            response.addError("The cost of the expense can not be less than 0");
            errorCount++;
        }

        if(dateOfExpense == null) {
            response.addError("The Date of the expense can not be null");
            errorCount++;
        } else if (dateOfExpense.after(new Date())) {
            response.addError("The date of the expense can not be in the future");
            errorCount++;
        }

        if(errorCount!=0)
            return response;

        /**
         * Storing the expense entity that has been added to the repository
         */
        Expense expense = expenseRepository.save(new Expense( request.getTitle()
                , convertToUSD( request.getCost(),request.getCurrency())
                , request.getDateOfExpense()
                , request.getCurrency()));

        /**
         *  Setting the title, cost and date of expense which has been stored in the repository
         */
        response.setTitle(expense.getTitle());
        response.setDateOfExpense(expense.getDateOfExpense());
        response.setCost(expense.getCost());
        response.setCurrency(expense.getCurrency());

        return response;
    }


    /**
     * Get all the expenses that are stored in the expense repository in the form of pages
     *
     * @param request contains the page number of the expenses
     *
     * @return a page of expenses
     */
    public GetAllExpensesResponse getAllExpenses(final GetAllExpensesRequest request) throws IOException {

        int pageSize = Math.max(request.getPageSize(), 100);

        Pageable pageable = PageRequest.of(request.getPageNumber(),
                                            pageSize,
                                            Sort.by("dateOfExpense").descending() );

        List<ExpenseSummary> expenseSummaries = new ArrayList<>();

        for(Expense expense : expenseRepository.findAll(pageable)){
            expenseSummaries.add(new ExpenseSummary(expense.getTitle(),
                                                        convertToExpenseCurrency(expense.getCost(),expense.getCurrency()),
                                                        expense.getDateOfExpense(),
                                                        expense.getCurrency()));
        }

        final long totalCount = expenseRepository.count();
        Page<ExpenseSummary> page = new PageImpl<>(expenseSummaries, pageable, totalCount);

        return new GetAllExpensesResponse(page);
    }


    /**
     * Get the month wise expense summary of the expenses
     *
     * @param request containing the number of months summary required and the start date of the months summaries
     *
     * @return list of MonthSummary objects
     */
    public GetAllExpenseSummaryResponse getAllExpensesSummary(final GetAllExpenseSummaryRequest request) {

        Date originalDate = new Date();

        LocalDate localDate = originalDate.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();

        LocalDate currentMonth = localDate.withDayOfMonth(1);

        List<MonthSummary> response=new ArrayList<>();

        for(int i=0; i<request.getNumberOfMonths() ;i++){

            response.add(new MonthSummary(
                    Date.from(currentMonth.atStartOfDay(ZoneId.of("UTC")).toInstant()),
                    expenseRepository.getExpensePerMonth(currentMonth.getYear()*100 + currentMonth.getMonthValue())));

            currentMonth = currentMonth.minusMonths(1);
        }

        return new GetAllExpenseSummaryResponse( response );
    }


    public BigDecimal convertToUSD(BigDecimal cost,Currency currency) throws IOException {
           return CurrencyConverter.convertToUSD( cost,currency);
    }

    public BigDecimal convertToExpenseCurrency(BigDecimal cost, Currency currency) throws IOException {
            return CurrencyConverter.convertBackFromUSD(cost,currency);
    }

}
