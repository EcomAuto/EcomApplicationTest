package utils;

import java.io.File;

public class AllureCleaner {
    public static void cleanAllureResults() {
        File allureResults = new File("target/allure-results");
        if (allureResults.exists()) {
            for (File file : allureResults.listFiles()) {
                file.delete();
            }
            System.out.println("Allure results cleaned successfully.");
        }
    }
}
