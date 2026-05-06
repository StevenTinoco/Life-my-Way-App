package com.canigetafiver.lifemyway.api;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserDataBase implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final HashMap<String, ExpenseAccount> userAccounts;
    private final HashMap<String, User> users;

    public UserDataBase() {
        this.userAccounts = new HashMap<>();
        this.users = new HashMap<>();
    }

    public User findUser(String userId) {
        return users.get(userId);
    }

    public ExpenseAccount findUserAccount(String userId) {
        return userAccounts.get(userId);
    }

    public void addUser(String userId, User user, ExpenseAccount account) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User id cannot be null or blank.");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        users.put(userId, user);
        userAccounts.put(userId, account == null ? new ExpenseAccount() : account);
    }

    public boolean deleteUser(String userId) {
        if (!users.containsKey(userId) && !userAccounts.containsKey(userId)) {
            return false;
        }
        users.remove(userId);
        userAccounts.remove(userId);
        return true;
    }

    public Map<String, User> getUsers() {
        return Map.copyOf(users);
    }

    public Map<String, ExpenseAccount> getUserAccounts() {
        return Map.copyOf(userAccounts);
    }
}
