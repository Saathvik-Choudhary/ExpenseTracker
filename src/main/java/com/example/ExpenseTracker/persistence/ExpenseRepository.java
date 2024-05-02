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
     * Get the sum of expenses in a particular calendar month
     *
     * @param i is the month id which is of format yyyyMM
     *
     * @return the total expenses in a particular calendar month
     */
    @Query("SELECT SUM(e.cost) "
            + "FROM Expense e "
            + "WHERE e.dateid = ?1")
    BigDecimal getExpensePerMonth(int i);
}
