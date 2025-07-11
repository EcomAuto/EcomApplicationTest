package tests;

import base.BaseTest;
import configuration.ConfigReader;
import io.qameta.allure.*;
import pageClass.CreateAccountPage;
import services.CreateAccountService;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AccountDataProvider;

@Epic("E-commerce Registration")
@Feature("Account Creation")
@Listeners(listeners.ScreenshotListener.class)
public class CreateAccountTest extends BaseTest {

    @Story("Register new user with valid details")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "accountData", dataProviderClass = AccountDataProvider.class, description = "Validate account creation")
    public void verifyAccountCreation(String fname, String lname, String email, String password) {
        driver.get(ConfigReader.get("createAccountUrl"));

        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        CreateAccountService service = new CreateAccountService(createAccountPage);

        service.registerNewUser(fname, lname, email, password);

        String successText = createAccountPage.getSuccessMessage();
        Assert.assertTrue(successText.contains("Thank you for registering"),
                "Success message not displayed. Message: " + successText);
    }
}
