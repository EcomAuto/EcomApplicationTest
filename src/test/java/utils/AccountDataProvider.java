package utils;

import org.testng.annotations.DataProvider;

public class AccountDataProvider {

    @DataProvider(name = "accountData")
    public Object[][] getData() {
        return new Object[][]{
        	{"John", "Doe", DataGenerator.generateRandomEmail("John", "Doe"), "Pass@12345"},
            {"Jane", "Smith", DataGenerator.generateRandomEmail("Jane", "Smith"), "Pass@12345"},
            {"Alice", "Walker", DataGenerator.generateRandomEmail("Alice", "Walker"), "Pass@12345"}
        };
    }
}

