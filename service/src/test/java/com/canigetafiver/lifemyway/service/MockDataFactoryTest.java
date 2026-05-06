package com.canigetafiver.lifemyway.service;

import com.canigetafiver.lifemyway.api.TestAssertions;

public class MockDataFactoryTest {
    public static void main(String[] args) {
        TestAssertions.assertEquals(10, MockDataFactory.createExpenses().size(), "factory should create ten expenses");
        TestAssertions.assertEquals(3, MockDataFactory.createCategories().size(), "factory should create three categories");
        TestAssertions.assertEquals(2, MockDataFactory.createUsers().size(), "factory should create two users");
        System.out.println("MockDataFactoryTest passed");
    }
}
