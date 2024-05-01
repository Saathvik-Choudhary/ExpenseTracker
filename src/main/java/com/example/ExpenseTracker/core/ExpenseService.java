package com.example.ExpenseTracker.core;

import com.example.ExpenseTracker.data.*;
import com.example.ExpenseTracker.domain.Expense;
import com.example.ExpenseTracker.persistence.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

/**
 * Main service class containing all the business logic of the expense tracking app
 */
@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    /**
     * Saves the expense in the expense repository
     *
     * @param request   contains all the details of the expense
     *
     * @return any errors if any, the saved details and the saved expense message if the expense is saved successfully
     */
    public SaveExpenseResponse saveExpense(final SaveExpenseRequest request){

        /*
        Validator validator;
        var validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            // If there are validation errors, create a response with errors
            SaveExpenseResponse errorResponse = new SaveExpenseResponse();
            validationErrors.forEach(error -> errorResponse.addError(error.getMessage()));
            return errorResponse;
        }
         */

        expenseRepository.save(new Expense( request.getTitle(),request.getCost(),request.getDateOfExpense()));

        return new SaveExpenseResponse(request.getTitle(),request.getCost(),request.getDateOfExpense(),request.getCurrency());
    }


    /**
     * Get all the expenses that are stored in the expense repository in the form of pages
     *
     * @param request contains the page number of the expenses
     *
     * @return a page of expenses
     */
    public GetAllExpensesResponse getAllExpenses(final GetAllExpensesRequest request){

        int pageSize = Math.max(request.getPageSize(), 100);

        Pageable pageable = PageRequest.of(request.getPageNumber()
                , pageSize
                , Sort.by("dateOfExpense").descending() );

        List<ExpenseSummary> expenseSummaries = new ArrayList<>();

        for(Expense expense : expenseRepository.findAll(pageable)){
            expenseSummaries.add(new ExpenseSummary(    expense.getTitle(),
                                                        expense.getCost(),
                                                        expense.getDateOfExpense()
            ));
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
    public GetAllExpenseSummaryResponse getAlLExpensesSummary(GetAllExpenseSummaryRequest request) {

        Date originalDate = new Date();

        LocalDate localDate = originalDate.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();

        LocalDate currentMonth = localDate.withDayOfMonth(1);

        List<MonthSummary> response=new ArrayList<>();

        for(int i=0;i<3;i++){
            response.add(new MonthSummary(
                    Date.from(currentMonth.atStartOfDay(ZoneId.of("UTC")).toInstant()),
                    expenseRepository.getExpensePerMonth(currentMonth.getYear()*100 + currentMonth.getMonthValue()))
            );

            currentMonth = currentMonth.minusMonths(1);
        }

        return new GetAllExpenseSummaryResponse( response );
    }
}
