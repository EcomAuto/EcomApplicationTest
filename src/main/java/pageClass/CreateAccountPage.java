package pageClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage {
    private WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By firstName = By.id("firstname");
    private final By lastName = By.id("lastname");
    private final By email = By.id("email_address");
    private final By password = By.id("password");
    private final By confirmPassword = By.id("password-confirmation");
    private final By createAccountBtn = By.cssSelector("button[title='Create an Account']");
    private final By successMessage = By.cssSelector("div.message-success.success.message");

    public void fillForm(String fname, String lname, String emailId, String pwd) {
        driver.findElement(firstName).sendKeys(fname);
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(email).sendKeys(emailId);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(confirmPassword).sendKeys(pwd);
    }

    public void submitForm() {
        driver.findElement(createAccountBtn).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}

