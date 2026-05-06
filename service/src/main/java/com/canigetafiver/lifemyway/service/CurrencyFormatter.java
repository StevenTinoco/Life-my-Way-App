package com.canigetafiver.lifemyway.service;

import java.util.Locale;
import java.util.Map;

public class CurrencyFormatter {
    private static final Map<String, String> SYMBOLS = Map.of(
            "USD", "$",
            "EUR", "€",
            "MXN", "MX$"
    );

    public String format(double amount, String currency) {
        String normalizedCurrency = normalizeCurrency(currency);
        return SYMBOLS.get(normalizedCurrency) + String.format(Locale.US, "%.2f", amount);
    }

    private String normalizeCurrency(String currency) {
        if (currency == null || currency.isBlank()) {
            return "USD";
        }

        String normalizedCurrency = currency.toUpperCase();
        if (!SYMBOLS.containsKey(normalizedCurrency)) {
            return "USD";
        }
        return normalizedCurrency;
    }
}
