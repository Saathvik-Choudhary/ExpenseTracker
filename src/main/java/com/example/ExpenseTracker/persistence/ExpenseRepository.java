package com.example.ExpenseTracker.persistence;

import com.example.ExpenseTracker.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * JPA repository to store the expense entities
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    /**
     * Get the sum of expenses in a particular calendar month.
     *
     * @param dateId is the month id which is of format yyyyMM.
     *
     * @return the total expenses in a particular calendar month.
     */
    @Query("SELECT SUM(e.cost) "
            + "FROM Expense e "
            + "WHERE e.dateId = ?1")
    BigDecimal getExpensePerMonth(int dateId);


    /**
     * Instead of the above query write a query using YearMonth Table and group the expenses based on the months
     *
     * this will enable you to handle the null values or the values not being in the months the sum was requested for
     *
     * COALESCE is the inbuilt keyword which will handle the null value being returned when there are no expenses
     * stored in the months the summary was requested for
     */
}
