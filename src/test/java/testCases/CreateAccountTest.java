package testCases;

import io.qameta.allure.*;
import models.UserModel;
import pageClass.CreateAccountPage;
import services.CreateAccountService;
import utils.AccountDataProvider;
import java.util.List;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import assertions.AssertUtils;
import base.BaseTest;
import configuration.ConfigReader;

@Epic("E-commerce Registration")
@Feature("Account Creation")
@Listeners({io.qameta.allure.testng.AllureTestNg.class,listeners.ScreenshotListener.class})
public class CreateAccountTest extends BaseTest {

    @Story("Register new user with valid details")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify Create Account using Valid Credentials")
    @Test(dataProvider = "accountData", dataProviderClass = AccountDataProvider.class, description = "Validate account creation")
    public void verifyAccountCreation(String useCase,String fname, String lname, String email, String password) {
//    	Logger logger = LogUtil.getTestCaseLogger("CreateAccountTest", "verifyAccountCreation");
        driver.get(ConfigReader.get("createAccountUrl"));

        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        logger.info("Starting valid account test with user input.");
        CreateAccountService service = new CreateAccountService(createAccountPage);
        UserModel user = new UserModel(useCase,fname, lname, email, password);
        service.registerNewUser(user);

        String successText = createAccountPage.getSuccessMessage();
        AssertUtils.assertTrue(successText.contains("Thank you for registering"),
                "Success message not displayed. Message: " + successText);
    }
    
    // Negative Test case for verifying CreateAccount Error
    @Story("Register new user with In-valid details")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify Create Account using In-Valid Credentials")
    @Test(dataProvider = "invalidUserData", dataProviderClass = AccountDataProvider.class,
            description = "Verify error messages for invalid user registration inputs")
      public void testNegativeAccountCreation(UserModel user, List<String> expectedErrors) {
//    	Logger logger = LogUtil.getLogger("CreateAccountTest", "testNegativeAccountCreation");
    	driver.get(ConfigReader.get("createAccountUrl"));
    	CreateAccountPage page = new CreateAccountPage(driver);
    	logger.info("Starting invalid account test with blank fields.");
        page.fillAccountForm(user);
        page.submitForm();

        List<String> actualErrors = page.getAllValidationErrors();

        Allure.step("Actual validation errors: " + actualErrors);
        Allure.step("Expected validation errors: " + expectedErrors);

        for (String expected : expectedErrors) {
            boolean match = actualErrors.stream().anyMatch(actual -> actual.contains(expected));
            AssertUtils.assertTrue(match, "Expected error not found: " + expected);
        }
        
    }
}

