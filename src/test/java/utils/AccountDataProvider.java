package utils;

import java.util.List;

import org.testng.annotations.DataProvider;

import models.UserModel;

public class AccountDataProvider {

    @DataProvider(name = "accountData")
    public Object[][] getData() {
        return new Object[][]{
        	{"Creating Account With Valid Details For Person: John","John", "Doe", DataGenerator.generateRandomEmail("John", "Doe"), "Pass@12345"},
            {"Creating Account With Valid Details For Person: Jane","Jane", "Smith", DataGenerator.generateRandomEmail("Jane", "Smith"), "Pass@12345"},
            {"Creating Account With Valid Details For Person: Alice","Alice", "Walker", DataGenerator.generateRandomEmail("Alice", "Walker"), "Pass@12345"}
        };
    }
    
    @DataProvider(name = "invalidUserData")
    public static Object[][] invalidUserData() {
        return new Object[][] {
            // [UserModel input, expected error message]
        	{
                new UserModel("Creating Account With Blank Fields","", "", "", ""),
                List.of("This is a required field.")
            },
            {
                new UserModel("Creating Account With In-Valid Email-Id","John", "Doe", "invalidemail@", "Pass@12345"),
                List.of("Please enter a valid email address (Ex: johndoe@domain.com).")
            },
            {
                new UserModel("Creating Account With In-Valid Password Of Length Less Than 8","Jane", "Smith", "jane.smith@test.com", "123"),
                List.of("Minimum length of this field must be equal or greater than 8 symbols.")
            },
            {
                new UserModel("Creating Account Without Entering Confirm Password","Jack", "Black", "jack.black" + System.currentTimeMillis() + "@mail.com", "Pass@12345"),
                List.of("Please enter the same value again.") // Assume confirm password not filled
            },
            {
                new UserModel("Creating Account With Existing Account","Existing", "User", "existing.user@test.com", "Pass@12345"),
                List.of("There is already an account with this email address.")
            },
            {
                new UserModel("Creating Account With Invalid emailId and Password less than 8 Characters","!@#", "$%^", "wrong@format", "short"),
                List.of(
                    "Please enter a valid email address (Ex: johndoe@domain.com).",
                    "Minimum length of this field must be equal or greater than 8 symbols."
                )
            },
            {
                new UserModel("Creating Account With Blank Spaces","   ", "   ", "    ", "    "),
                List.of("This is a required field.")
            }
        };
    }
}

