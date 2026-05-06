package com.canigetafiver.lifemyway.service;

import com.canigetafiver.lifemyway.api.ExpenseAccount;
import com.canigetafiver.lifemyway.api.TestAssertions;
import com.canigetafiver.lifemyway.api.User;
import com.canigetafiver.lifemyway.api.UserDataBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersistenceManagerTest {
    public static void main(String[] args) throws IOException {
        Path tempDir = Files.createTempDirectory("lifemyway-persistence-test");
        savesAndLoadsEmptyDatabase(tempDir);
        savesAndLoadsDatabaseWithMultipleUsersAndExpenses(tempDir);
        loadRestoresFromTempWhenMainFileIsMissing(tempDir);
        missingFilesReturnEmptyDatabase(tempDir);
        System.out.println("PersistenceManagerTest passed");
    }

    private static void savesAndLoadsEmptyDatabase(Path tempDir) throws IOException {
        PersistenceManager persistenceManager = new PersistenceManager();
        Path dataPath = tempDir.resolve("empty.db");

        persistenceManager.saveUserData(new UserDataBase(), dataPath.toString());
        UserDataBase loaded = persistenceManager.loadUserData(dataPath.toString());

        TestAssertions.assertTrue(loaded.getUsers().isEmpty(), "empty database should have no users");
        TestAssertions.assertTrue(loaded.getUserAccounts().isEmpty(), "empty database should have no accounts");
    }

    private static void savesAndLoadsDatabaseWithMultipleUsersAndExpenses(Path tempDir) throws IOException {
        PersistenceManager persistenceManager = new PersistenceManager();
        Path dataPath = tempDir.resolve("users.db");
        UserDataBase db = MockDataFactory.createUserDataBase();

        persistenceManager.saveUserData(db, dataPath.toString());
        UserDataBase loaded = persistenceManager.loadUserData(dataPath.toString());

        TestAssertions.assertEquals("Steven Tinoco Calvillo", loaded.findUser("steven").getUserName(), "loaded Steven user name should match");
        TestAssertions.assertEquals("MXN", loaded.findUser("demo").getPreferredCurrency(), "loaded demo currency should match");
        TestAssertions.assertEquals(10, loaded.findUserAccount("steven").getExpenses().size(), "Steven account should keep expenses");
    }

    private static void loadRestoresFromTempWhenMainFileIsMissing(Path tempDir) throws IOException {
        PersistenceManager persistenceManager = new PersistenceManager();
        Path originalPath = tempDir.resolve("original.db");
        Path recoveredPath = tempDir.resolve("recovered.db");
        UserDataBase db = new UserDataBase();
        db.addUser("demo", new User("Demo", "demo@example.com", "555-0102", "USD"), new ExpenseAccount());

        persistenceManager.saveUserData(db, originalPath.toString());
        Files.copy(originalPath, Path.of(recoveredPath + ".tmp"));

        UserDataBase recovered = persistenceManager.loadUserData(recoveredPath.toString());

        TestAssertions.assertEquals("Demo", recovered.findUser("demo").getUserName(), "temp restore should load demo user");
        TestAssertions.assertTrue(Files.exists(recoveredPath), "restore should recreate main file");
    }

    private static void missingFilesReturnEmptyDatabase(Path tempDir) throws IOException {
        PersistenceManager persistenceManager = new PersistenceManager();

        UserDataBase loaded = persistenceManager.loadUserData(tempDir.resolve("missing.db").toString());

        TestAssertions.assertTrue(loaded.getUsers().isEmpty(), "missing file should return empty database");
    }
}
