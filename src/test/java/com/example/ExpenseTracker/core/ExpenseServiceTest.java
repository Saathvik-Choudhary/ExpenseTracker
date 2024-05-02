package com.example.ExpenseTracker.core;

import com.example.ExpenseTracker.data.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
public class ExpenseServiceTest {


    @Autowired
    ExpenseService expenseService;

    @Test
    public void assetCountTest(){

        Assertions.assertEquals(expenseService.expenseRepository.count(),11);
    }

    @Test
    public void saveAssetTest() throws IOException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = dateFormat.parse("2024-05-01T00:00:00.000+00:00");
        var expense= expenseService.saveExpense(new SaveExpenseRequest("Tv", new BigDecimal("5030.26"), date , Currency.getInstance("USD")));

        Assertions.assertEquals(expense.getCost(),BigDecimal.valueOf(5030.26));
        Assertions.assertEquals(expense.getTitle(),"Tv");
        Assertions.assertEquals(expense.getDateOfExpense(),date);
    }

    @Test
    public void expenseSummaryTest(){
        GetAllExpenseSummaryRequest request=new GetAllExpenseSummaryRequest(3);
        List<MonthSummary> monthSummaries= expenseService.getAllExpensesSummary(request).getMonthSummaries();

        Assertions.assertEquals(monthSummaries.get(0).getTotalExpenses(), BigDecimal.valueOf(5080.51));
        Assertions.assertEquals(monthSummaries.get(1).getTotalExpenses(), BigDecimal.valueOf(261.25));
        Assertions.assertEquals(monthSummaries.get(2).getTotalExpenses(), BigDecimal.valueOf(221.01));
    }

    @Test
    public void getAllExpensesTest(){
        Page<ExpenseSummary> page=expenseService.getAllExpenses(new GetAllExpensesRequest()).getPage();

        Assertions.assertEquals(page.getSize(),100);
        Assertions.assertEquals(page.getTotalElements(),11);
    }


    @Test
    public void saveExpenseWithBlankTitle() throws ParseException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = dateFormat.parse("2024-05-01T00:00:00.000+00:00");
        var expense= expenseService.saveExpense(new SaveExpenseRequest("  ", new BigDecimal("5030.26"), date , Currency.getInstance("USD")));

        Assertions.assertEquals(expense.getErrors().get(0),"The title of the expense can not be blank");
    }

    @Test
    public void saveExpenseWithNegativeCost() throws ParseException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = dateFormat.parse("2024-05-01T00:00:00.000+00:00");
        var expense= expenseService.saveExpense(new SaveExpenseRequest("title", new BigDecimal("-5030.26"), date , Currency.getInstance("USD")));

        Assertions.assertEquals(expense.getErrors().get(0),"The cost of the expense can not be less than 0");
    }

    @Test
    public void saveExpenseWithFutureDate() throws ParseException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = dateFormat.parse("2024-06-01T00:00:00.000+00:00");
        var expense= expenseService.saveExpense(new SaveExpenseRequest("title", new BigDecimal("5030.26"), date , Currency.getInstance("USD")));

        Assertions.assertEquals(expense.getErrors().get(0),"The date of the expense can not be in the future");
    }


    public void init() throws IOException {
        List<SaveExpenseRequest> expenses = new ArrayList<>();

        expenses.add(new SaveExpenseRequest("Groceries", new BigDecimal("50.25"), getRandomDate(), Currency.getInstance("USD")));
        expenses.add(new SaveExpenseRequest("Utilities", new BigDecimal("120.75"), getRandomDate(), Currency.getInstance("USD")));
        expenses.add(new SaveExpenseRequest("Dining Out", new BigDecimal("80.50"), getRandomDate(), Currency.getInstance("USD")));
        expenses.add(new SaveExpenseRequest("Transportation", new BigDecimal("45.00"), getRandomDate(), Currency.getInstance("USD")));
        expenses.add(new SaveExpenseRequest("Entertainment", new BigDecimal("60.00"), getRandomDate(), Currency.getInstance("USD")));
        expenses.add(new SaveExpenseRequest("Clothing", new BigDecimal("100.20"), getRandomDate(), Currency.getInstance("USD")));
        expenses.add(new SaveExpenseRequest("Healthcare", new BigDecimal("75.80"), getRandomDate(), Currency.getInstance("USD")));
        expenses.add(new SaveExpenseRequest("Education", new BigDecimal("200.00"), getRandomDate(), Currency.getInstance("USD")));
        expenses.add(new SaveExpenseRequest("Insurance", new BigDecimal("150.35"), getRandomDate(), Currency.getInstance("USD")));
        expenses.add(new SaveExpenseRequest("Rent", new BigDecimal("1000.00"), getRandomDate(), Currency.getInstance("USD")));

        for (SaveExpenseRequest expenseRequest : expenses) {
            SaveExpenseResponse response = expenseService.saveExpense(expenseRequest);
        }
    }

    public Date getRandomDate() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Set the calendar to 4 months ago
        calendar.add(Calendar.MONTH, -4);
        Date fourMonthsAgo = calendar.getTime();

        // Generate a random date between 4 months ago and today
        long randomTime = ThreadLocalRandom.current().nextLong(fourMonthsAgo.getTime(), currentDate.getTime());
        return new Date(randomTime);
    }
}