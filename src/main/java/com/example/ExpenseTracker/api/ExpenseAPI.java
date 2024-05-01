package com.example.ExpenseTracker.api;

import com.example.ExpenseTracker.core.ExpenseService;
import com.example.ExpenseTracker.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/expenses")
public class ExpenseAPI {

    @Autowired
    ExpenseService expenseService;

    @CrossOrigin
    @GetMapping("/summary/{numberOfMonths}")
    public ResponseEntity<GetAllExpenseSummaryResponse> getExpenseSummary(@PathVariable final int numberOfMonths) {
        final GetAllExpenseSummaryRequest request = new GetAllExpenseSummaryRequest(numberOfMonths);
        return ResponseEntity.ok(expenseService.getAllExpensesSummary(request));
    }

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity< Page<ExpenseSummary>> getAllAssets(final GetAllExpensesRequest request){
        return ResponseEntity.ok( expenseService.getAllExpenses(request).getPage());
    }

    @CrossOrigin
    @PutMapping("")
    public ResponseEntity<SaveExpenseResponse> saveExpense(@RequestBody final SaveExpenseRequest request){

        return ResponseEntity.ok(expenseService.saveExpense(request));
    }

    @CrossOrigin
    @GetMapping("/currencies")
    public ResponseEntity<Set<Currency>> getCurrencies(){
        return ResponseEntity.ok( Currency.getAvailableCurrencies());
    }
}
