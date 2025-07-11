package utils;

import java.util.UUID;

public class DataGenerator {

	public static String generateRandomEmail(String firstName, String lastName) {
        String uniqueId = UUID.randomUUID().toString().substring(0, 5);
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + uniqueId + "@test.com";
    }
}

