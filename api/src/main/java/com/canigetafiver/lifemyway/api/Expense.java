package com.canigetafiver.lifemyway.api;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Expense implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String description;
    private double amount;
    private Category category;
    private LocalDate date;

    public Expense(String description, double amount, Category category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
