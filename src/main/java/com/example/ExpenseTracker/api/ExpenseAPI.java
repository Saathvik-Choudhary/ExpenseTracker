package com.example.ExpenseTracker.api;

import com.example.ExpenseTracker.core.ExpenseService;
import com.example.ExpenseTracker.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/expense")
public class ExpenseAPI {

    @Autowired
    ExpenseService expenseService;


    @GetMapping("/summary")
    public ResponseEntity<GetAllExpenseSummaryResponse> saveExpense(@RequestBody final GetAllExpenseSummaryRequest request) {
        return ResponseEntity.ok(expenseService.getAlLExpensesSummary(request));
    }


    @GetMapping("/all")
    public ResponseEntity< Page<ExpenseSummary>> getAllAssets(@RequestBody final GetAllExpensesRequest request){
        return ResponseEntity.ok( expenseService.getAllExpenses(request).getPage());
    }

    @PostMapping( "/save")
    public ResponseEntity<SaveExpenseResponse> saveExpense(@RequestBody final SaveExpenseRequest request){

        return ResponseEntity.ok(expenseService.saveExpense(request));
    }
}
