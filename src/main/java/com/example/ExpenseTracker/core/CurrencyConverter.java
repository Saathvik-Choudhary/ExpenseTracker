package com.example.ExpenseTracker.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;

import org.json.JSONObject;

public interface CurrencyConverter {

    public static BigDecimal convertToUSD(BigDecimal amount, Currency fromCurrency) throws IOException {
            URL url = new URL("https://api.exchangerate-api.com/v4/latest/USD");
            JSONObject jsonResponse = getJsonObject(url);
            JSONObject rates = jsonResponse.getJSONObject("rates");
            double exchangeRate = rates.getDouble(fromCurrency.toString());

            return amount.divide( BigDecimal.valueOf(exchangeRate),fromCurrency.getDefaultFractionDigits(), RoundingMode.HALF_UP);
    }

    private static JSONObject getJsonObject(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return new JSONObject(response.toString());
    }

    public static BigDecimal convertBackFromUSD(BigDecimal amount, Currency fromCurrency) throws IOException {
        URL url = new URL("https://api.exchangerate-api.com/v4/latest/USD");
        JSONObject jsonResponse = getJsonObject(url);
        JSONObject rates = jsonResponse.getJSONObject("rates");
        double exchangeRate = rates.getDouble(fromCurrency.toString());

        return amount.multiply( BigDecimal.valueOf(exchangeRate));
    }
}
