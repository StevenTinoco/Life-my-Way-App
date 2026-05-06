package com.canigetafiver.lifemyway.service;

import com.canigetafiver.lifemyway.api.Category;
import com.canigetafiver.lifemyway.api.Expense;
import com.canigetafiver.lifemyway.api.ExpenseAccount;
import com.canigetafiver.lifemyway.api.User;
import com.canigetafiver.lifemyway.api.UserDataBase;

import java.time.LocalDate;
import java.util.List;

public final class MockDataFactory {
    private MockDataFactory() {
    }

    public static List<Category> createCategories() {
        return List.of(
                new Category("Food", "Groceries, restaurants, and snacks"),
                new Category("Transportation", "Fuel, transit, and rideshare"),
                new Category("Utilities", "Recurring household bills")
        );
    }

    public static List<Expense> createExpenses() {
        List<Category> categories = createCategories();
        return List.of(
                new Expense("Groceries", 64.32, categories.get(0), LocalDate.of(2026, 1, 5)),
                new Expense("Coffee", 4.75, categories.get(0), LocalDate.of(2026, 1, 6)),
                new Expense("Lunch", 12.99, categories.get(0), LocalDate.of(2026, 1, 7)),
                new Expense("Gas", 42.10, categories.get(1), LocalDate.of(2026, 1, 8)),
                new Expense("Bus pass", 25.00, categories.get(1), LocalDate.of(2026, 1, 9)),
                new Expense("Rideshare", 18.45, categories.get(1), LocalDate.of(2026, 1, 10)),
                new Expense("Electric bill", 83.20, categories.get(2), LocalDate.of(2026, 1, 11)),
                new Expense("Water bill", 35.80, categories.get(2), LocalDate.of(2026, 1, 12)),
                new Expense("Internet", 59.99, categories.get(2), LocalDate.of(2026, 1, 13)),
                new Expense("Phone bill", 49.50, categories.get(2), LocalDate.of(2026, 1, 14))
        );
    }

    public static List<User> createUsers() {
        return List.of(
                new User("Steven Tinoco Calvillo", "steven@example.com", "555-0101", "USD"),
                new User("Demo User", "demo@example.com", "555-0102", "MXN")
        );
    }

    public static UserDataBase createUserDataBase() {
        UserDataBase userDataBase = new UserDataBase();
        List<User> users = createUsers();
        userDataBase.addUser("steven", users.get(0), new ExpenseAccount(createExpenses()));
        userDataBase.addUser("demo", users.get(1), new ExpenseAccount());
        return userDataBase;
    }
}
