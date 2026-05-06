package com.canigetafiver.lifemyway.web;

import com.canigetafiver.lifemyway.api.ExpenseAccount;
import com.canigetafiver.lifemyway.api.User;
import com.canigetafiver.lifemyway.api.UserDataBase;
import com.canigetafiver.lifemyway.service.PersistenceManager;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UserSettingsController {
    private final User user;
    private final UserDataBase userDataBase;
    private final PersistenceManager persistenceManager;
    private final String userId;
    private final String dataPath;

    private TextField nameField;
    private TextField emailField;
    private TextField phoneField;
    private ComboBox<String> currencyDropdown;
    private Label statusLabel;

    public UserSettingsController(User user, UserDataBase userDataBase, PersistenceManager persistenceManager,
                                  String userId, String dataPath) {
        this.user = user;
        this.userDataBase = userDataBase;
        this.persistenceManager = persistenceManager;
        this.userId = userId;
        this.dataPath = dataPath;
    }

    public Parent createView() {
        nameField = new TextField(user.getUserName());
        emailField = new TextField(user.getUserEmail());
        phoneField = new TextField(user.getUserNumber());
        currencyDropdown = new ComboBox<>(FXCollections.observableArrayList("USD", "EUR", "MXN"));
        currencyDropdown.setValue(user.getPreferredCurrency());
        statusLabel = new Label();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveSettings());

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, new Label("Name"), nameField);
        form.addRow(1, new Label("Email"), emailField);
        form.addRow(2, new Label("Phone Number"), phoneField);
        form.addRow(3, new Label("Preferred Currency"), currencyDropdown);

        VBox root = new VBox(12, form, saveButton, statusLabel);
        root.setPadding(new Insets(16));
        return root;
    }

    private void saveSettings() {
        try {
            user.setUserName(nameField.getText());
            user.setUserEmail(emailField.getText());
            user.setUserNumber(phoneField.getText());
            user.setPreferredCurrency(currencyDropdown.getValue());

            ExpenseAccount existingAccount = userDataBase.findUserAccount(userId);
            userDataBase.addUser(userId, user, existingAccount);
            persistenceManager.saveUserData(userDataBase, dataPath);
            statusLabel.setText("Settings saved successfully.");
        } catch (IllegalArgumentException invalidInput) {
            statusLabel.setText(invalidInput.getMessage());
        } catch (IOException saveFailed) {
            statusLabel.setText("Settings could not be saved.");
        }
    }
}
