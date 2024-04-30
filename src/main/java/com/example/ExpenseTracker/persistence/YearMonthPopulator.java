package com.example.ExpenseTracker.persistence;

import com.example.ExpenseTracker.domain.YearMonth;
import com.example.ExpenseTracker.persistence.YearMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class YearMonthPopulator{}

/**
@Repository
public class YearMonthPopulator implements CommandLineRunner {

    @Autowired
    YearMonthRepository yearMonthRepository;

    @Override
    public void run(String... args) throws Exception {
        List<YearMonth> yearMonths = new ArrayList<>();

        // Loop through years from 1900 to 2100
        for (int year = 1900; year <= 2100; year++) {
            // Loop through months (January to December)
            for (int month = 1; month <= 12; month++) {
                // Create a YearMonth object for the current year and month
                YearMonth yearMonth = new YearMonth(year, month);
                yearMonths.add(yearMonth);
            }
        }

        // Save all YearMonth objects to the repository
        yearMonthRepository.saveAll(yearMonths);
    }
}
*/