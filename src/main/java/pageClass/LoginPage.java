package pageClass;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wait.WaitUtil;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath= "/html/body/div[2]/header/div[1]/div/ul/li[2]/a")
    private WebElement signInUrl;
    
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "pass")
    private WebElement passwordField;

    @FindBy(xpath = "/html/body/div[2]/header/div[1]/div/ul/li[2]/a")
    private WebElement signInButton;

    @FindBy(css = ".welcome-msg")
    private WebElement welcomeMsg;
    
    @FindBy(css = "div.message-error > div")
    private WebElement errorMessage;
    
    @Step("Click On Sign-In to Navigate to SignIn Page")
    public void signInToPage() {
    	WaitUtil.waitForClickable(driver, signInUrl, 10).click();
    }

    @Step("Enter email: {0}")
    public void enterEmail(String email) {
        WaitUtil.waitForVisibility(driver, emailField, 10).sendKeys(email);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        WaitUtil.waitForVisibility(driver, passwordField, 10).sendKeys(password);
    }

    @Step("Click sign in")
    public void clickSignIn() {
        WaitUtil.waitForClickable(driver, signInButton, 10).click();
    }

    @Step("Get welcome message")
    public String getWelcomeMessage() {
        return WaitUtil.waitForVisibility(driver, welcomeMsg, 10).getText();
    }
    
    @Step("Get error message after entering invalid login credentials")
    public String getErrorMessage() {
        return errorMessage.getText().trim();
    }

    public void login(String email, String password) {
    	signInToPage();
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }
}
