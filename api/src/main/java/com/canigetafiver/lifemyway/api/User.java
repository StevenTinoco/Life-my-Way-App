package com.canigetafiver.lifemyway.api;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String userName;
    private String userEmail;
    private String userNumber;
    private String preferredCurrency;

    public User(String userName, String userEmail, String userNumber, String preferredCurrency) {
        this.userName = userName;
        setUserEmail(userEmail);
        this.userNumber = userNumber;
        setPreferredCurrency(preferredCurrency);
    }

    public User(String userName) {
        this(userName, "unknown@example.com", "", "USD");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        if (userEmail == null || userEmail.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }
        this.userEmail = userEmail;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getPreferredCurrency() {
        return preferredCurrency;
    }

    public void setPreferredCurrency(String preferredCurrency) {
        if (preferredCurrency == null || preferredCurrency.isBlank()) {
            this.preferredCurrency = "USD";
            return;
        }
        this.preferredCurrency = preferredCurrency.toUpperCase();
    }

    public String getName() {
        return getUserName();
    }

    public String getEmail() {
        return getUserEmail();
    }
}
