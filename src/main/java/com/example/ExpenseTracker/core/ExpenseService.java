package com.example.ExpenseTracker.core;

import com.example.ExpenseTracker.data.*;
import com.example.ExpenseTracker.domain.Expense;
import com.example.ExpenseTracker.persistence.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public SaveExpenseResponse saveExpense(final SaveExpenseRequest request){

        expenseRepository.save(new Expense( request.getTitle(),request.getCost(),request.getDateOfExpense()));

        return new SaveExpenseResponse();
    }

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
