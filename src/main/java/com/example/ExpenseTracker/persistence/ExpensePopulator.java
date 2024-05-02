package com.example.ExpenseTracker.persistence;

import com.example.ExpenseTracker.domain.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


/**
 * This class populates the expense repository with 10 sample expenses with random dates
 */
@Repository
public class ExpensePopulator implements CommandLineRunner {

    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");


        Date date1 = dateFormat.parse("2024-05-01T00:00:00.000+00:00");
        Expense expense1 = new Expense("Groceries", new BigDecimal("50.25"), date1, Currency.getInstance("USD"));
        expenseRepository.save(expense1);

        Date date2 = dateFormat.parse("2024-04-01T00:00:00.000+00:00");
        Expense expense2 = new Expense("Utilities", new BigDecimal("120.75"), date2, Currency.getInstance("USD"));
        expenseRepository.save(expense2);

        Date date3 = dateFormat.parse("2024-04-03T00:00:00.000+00:00");
        Expense expense3 = new Expense("Dining Out", new BigDecimal("80.50"), date3, Currency.getInstance("USD"));
        expenseRepository.save(expense3);

        Date date4 = dateFormat.parse("2024-03-05T00:00:00.000+00:00");
        Expense expense4 = new Expense("Transportation", new BigDecimal("45.00"), date4, Currency.getInstance("USD"));
        expenseRepository.save(expense4);

        Date date5 = dateFormat.parse("2024-04-09T00:00:00.000+00:00");
        Expense expense5 = new Expense("Entertainment", new BigDecimal("60.00"), date5, Currency.getInstance("USD"));
        expenseRepository.save(expense5);

        Date date6 = dateFormat.parse("2024-03-19T00:00:00.000+00:00");
        Expense expense6 = new Expense("Clothing", new BigDecimal("100.20"), date6, Currency.getInstance("USD"));
        expenseRepository.save(expense6);

        Date date7 = dateFormat.parse("2024-03-12T00:00:00.000+00:00");
        Expense expense7 = new Expense("Healthcare", new BigDecimal("75.81"), date7, Currency.getInstance("USD"));
        expenseRepository.save(expense7);

        Date date8 = dateFormat.parse("2024-02-04T00:00:00.000+00:00");
        Expense expense8 = new Expense("Education", new BigDecimal("200.00"), date8, Currency.getInstance("USD"));
        expenseRepository.save(expense8);

        Date date9 = dateFormat.parse("2024-02-12T00:00:00.000+00:00");
        Expense expense9 = new Expense("Insurance", new BigDecimal("150.35"), date9, Currency.getInstance("USD"));
        expenseRepository.save(expense9);

        Date date10 = dateFormat.parse("2024-02-29T00:00:00.000+00:00");
        Expense expense10 = new Expense("Rent", new BigDecimal("1000.00"), date10, Currency.getInstance("USD"));
        expenseRepository.save(expense10);

    }

    /**
     * Get a random date in the past 4 months
     *
     * @return  a random date in the past 4 months
     */
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
