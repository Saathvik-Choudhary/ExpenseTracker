package com.example.ExpenseTracker.persistence;

import com.example.ExpenseTracker.domain.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Calendar;
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


        Expense expense1 = new Expense("Groceries", new BigDecimal("50.25"), getRandomDate());
        expenseRepository.save(expense1);

        Expense expense2 = new Expense("Utilities", new BigDecimal("120.75"), getRandomDate());
        expenseRepository.save(expense2);

        Expense expense3 = new Expense("Dining Out", new BigDecimal("80.50"), getRandomDate());
        expenseRepository.save(expense3);

        Expense expense4 = new Expense("Transportation", new BigDecimal("45.00"), getRandomDate());
        expenseRepository.save(expense4);

        Expense expense5 = new Expense("Entertainment", new BigDecimal("60.00"), getRandomDate());
        expenseRepository.save(expense5);

        Expense expense6 = new Expense("Clothing", new BigDecimal("100.20"), getRandomDate());
        expenseRepository.save(expense6);

        Expense expense7 = new Expense("Healthcare", new BigDecimal("75.80"), getRandomDate());
        expenseRepository.save(expense7);

        Expense expense8 = new Expense("Education", new BigDecimal("200.00"), getRandomDate());
        expenseRepository.save(expense8);

        Expense expense9 = new Expense("Insurance", new BigDecimal("150.35"), getRandomDate());
        expenseRepository.save(expense9);

        Expense expense10 = new Expense("Rent", new BigDecimal("1000.00"), getRandomDate());
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
