package com.example.ExpenseTracker.persistence;

import com.example.ExpenseTracker.domain.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

@Repository
public class YearMonthPopulator implements CommandLineRunner {

    @Autowired
    YearMonthRepository yearMonthRepository;


    @Override
    public void run(String... args) throws Exception {
        pupulateYearMonth();
    }

    private void pupulateYearMonth() {
        for(short i=1900;i<2101;i++){
            for(short j=1;j<13;j++){
                yearMonthRepository.save(new YearMonth( i,
                        j,
                        i * 100 + j));
            }
        }
    }
}
