package com.example.ExpenseTracker.persistence;

import com.example.ExpenseTracker.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long>, JpaSpecificationExecutor {

    @Query("SELECT SUM(e.cost) FROM Expense e WHERE e.dateid = ?1")
    BigDecimal getExpensePerMonth(int i);
}
