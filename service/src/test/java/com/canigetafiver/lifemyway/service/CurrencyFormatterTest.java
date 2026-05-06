package com.canigetafiver.lifemyway.service;

import com.canigetafiver.lifemyway.api.TestAssertions;

public class CurrencyFormatterTest {
    public static void main(String[] args) {
        CurrencyFormatter formatter = new CurrencyFormatter();
        TestAssertions.assertEquals("$12.50", formatter.format(12.5, "USD"), "USD should use dollar prefix");
        TestAssertions.assertEquals("€12.50", formatter.format(12.5, "EUR"), "EUR should use euro prefix");
        TestAssertions.assertEquals("MX$12.50", formatter.format(12.5, "MXN"), "MXN should use MX dollar prefix");
        TestAssertions.assertEquals("$12.50", formatter.format(12.5, "CAD"), "unknown currency should default to USD");
        System.out.println("CurrencyFormatterTest passed");
    }
}
