package com.canigetafiver.lifemyway.api;

public class UserDataBaseTest {
    public static void main(String[] args) {
        addUserStoresUserAndAccount();
        findUserReturnsMatchingUser();
        deleteUserRemovesUserAndAccount();
        missingKeysReturnNullAndDeleteReturnsFalse();
        System.out.println("UserDataBaseTest passed");
    }

    private static void addUserStoresUserAndAccount() {
        UserDataBase db = new UserDataBase();
        User user = new User("Steven", "steven@example.com", "555-0101", "USD");
        ExpenseAccount account = new ExpenseAccount();

        db.addUser("steven", user, account);

        TestAssertions.assertSame(user, db.findUser("steven"), "added user should be found");
        TestAssertions.assertSame(account, db.findUserAccount("steven"), "added account should be found");
    }

    private static void findUserReturnsMatchingUser() {
        UserDataBase db = new UserDataBase();
        User user = new User("Demo", "demo@example.com", "555-0102", "EUR");
        db.addUser("demo", user, new ExpenseAccount());

        TestAssertions.assertEquals("Demo", db.findUser("demo").getUserName(), "user name should match");
    }

    private static void deleteUserRemovesUserAndAccount() {
        UserDataBase db = new UserDataBase();
        db.addUser("demo", new User("Demo", "demo@example.com", "555-0102", "EUR"), new ExpenseAccount());

        TestAssertions.assertTrue(db.deleteUser("demo"), "delete should report success");
        TestAssertions.assertNull(db.findUser("demo"), "deleted user should be missing");
        TestAssertions.assertNull(db.findUserAccount("demo"), "deleted account should be missing");
    }

    private static void missingKeysReturnNullAndDeleteReturnsFalse() {
        UserDataBase db = new UserDataBase();

        TestAssertions.assertNull(db.findUser("missing"), "missing user should return null");
        TestAssertions.assertNull(db.findUserAccount("missing"), "missing account should return null");
        TestAssertions.assertFalse(db.deleteUser("missing"), "missing delete should return false");
    }
}
