package com.canigetafiver.lifemyway.api;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseAccount implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Expense> expenses;

    public ExpenseAccount() {
        this.expenses = new ArrayList<>();
    }

    public ExpenseAccount(List<Expense> expenses) {
        this.expenses = new ArrayList<>();
        if (expenses != null) {
            this.expenses.addAll(expenses);
        }
    }

    public void addExpense(Expense expense) {
        if (expense != null) {
            expenses.add(expense);
        }
    }

    public boolean removeExpense(Expense expense) {
        return expenses.remove(expense);
    }

    public List<Expense> getExpenses() {
        return Collections.unmodifiableList(expenses);
    }
}
