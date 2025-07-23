package testCases;

import java.util.UUID;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import assertions.AssertUtils;
import base.BaseTest;
import configuration.ConfigReader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.UserModel;
import pageClass.CreateAccountPage;
import pageClass.LoginPage;
import services.LoginAccountService;
import util.TestStepTracker;
import utils.LoginDataProvider;

@Epic("E-commerce Login")
@Feature("Account Login")
@Listeners({io.qameta.allure.testng.AllureTestNg.class,listeners.ScreenshotListener.class})
public class LoginTest extends BaseTest {
	@Story("Login with valid credentials after Creating Account")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Positive login test after account creation")
    @Test(dataProvider = "loginAccountData", dataProviderClass = LoginDataProvider.class, description = "Validate Login credential after Account Creation")
    public void testValidLoginAfterCreatingAccount(String useCase,String fname, String lname, String email, String password) {
		driver.get(ConfigReader.get("createAccountUrl"));
		logger.info("Starting valid account test with user input.");
		CreateAccountPage createAccountPage = new CreateAccountPage(driver);
		LoginPage loginPage = new LoginPage(driver);
        LoginAccountService service = new LoginAccountService(createAccountPage,loginPage);
        UserModel user = new UserModel(useCase,fname, lname, email, password);
        service.validateLogin(user);
		
		// Login with created user
        String welcome_Message = loginPage.getWelcomeMessage();
        String expected_Message = service.getWelcomeMessage();
		AssertUtils.assertEquals(welcome_Message, expected_Message,"Login Successful");
    }
	
	@Story("Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Positive login test")
    @Test(dataProvider = "verifyLoginAccountData", dataProviderClass = LoginDataProvider.class, description = "Validate Login credential")
	public void testValidLogin(String useCase,String userName, String password) {
		
		driver.get(ConfigReader.get("baseUrl"));
		String uuid = UUID.randomUUID().toString();
		LoginPage loginPage = new LoginPage(driver);
		
		TestStepTracker.logStep(uuid+" : Navigate to SignIn Page");
		loginPage.signInToPage();
		TestStepTracker.logStep(uuid+" : Enter email");
		loginPage.enterEmail(userName);
		TestStepTracker.logStep(uuid+": Enter Password");
		loginPage.enterPassword(password);
		TestStepTracker.logStep(uuid+" : Click Sign-In");
		loginPage.clickSignIn();
		TestStepTracker.logStep(uuid+" : Verify page title is 'Customer Login'");
		String actualTitle = loginPage.getPageTitle();
		AssertUtils.assertEquals(actualTitle, "Customer Login", "Login Page Title Check");
	}
}
