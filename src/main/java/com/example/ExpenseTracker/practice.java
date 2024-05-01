package com.example.ExpenseTracker;

import java.util.Currency;

public class practice {

    public static void main(String[] arge){
        final var currencies = Currency.getAvailableCurrencies();

        for(var i:currencies){
            System.out.println(i.getNumericCode());
        }
    }
}
