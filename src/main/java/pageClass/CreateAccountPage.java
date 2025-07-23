package pageClass;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import models.UserModel;
import wait.WaitUtil;

public class CreateAccountPage {

    private WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstname")
    private WebElement firstNameInput;

    @FindBy(id = "lastname")
    private WebElement lastNameInput;

    @FindBy(id = "email_address")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "password-confirmation")
    private WebElement confirmPasswordInput;

    @FindBy(css = "button[title='Create an Account']")
    private WebElement createAccountButton;

    @FindBy(css = ".message-success div")
    private WebElement successMessage;

    @FindBy(id = "firstname-error")
    private WebElement firstNameError;

    @FindBy(id = "lastname-error")
    private WebElement lastNameError;

    @FindBy(id = "email_address-error")
    private WebElement emailError;

    @FindBy(id = "password-error")
    private WebElement passwordError;

    @FindBy(id = "password-confirmation-error")
    private WebElement confirmPasswordError;

    @FindBy(css = "div.message-error div")
    private WebElement generalError;
    
    @FindBy(xpath= "/html/body/div[2]/header/div[1]/div/ul/li[1]/span")
    private WebElement logOut;
    
    @FindBy(css="div.panel.header > ul > li.customer-welcome")
    private WebElement dropDownAccount;

    public void fillAccountForm(UserModel user) {
        WaitUtil.waitForVisibility(driver, firstNameInput, 10).sendKeys(user.getFirstName());
        WaitUtil.waitForVisibility(driver, lastNameInput, 10).sendKeys(user.getLastName());
        WaitUtil.waitForVisibility(driver, emailInput, 10).sendKeys(user.getEmail());
        WaitUtil.waitForVisibility(driver, passwordInput, 10).sendKeys(user.getPassword());
        WaitUtil.waitForVisibility(driver, confirmPasswordInput, 10).sendKeys(user.getPassword());
    }

    public void submitForm() {
        WaitUtil.waitForClickable(driver, createAccountButton, 10).click();
    }
    
    public void logOutAfterAccountCreation() {
    	// Hover over the account dropdown
        WebElement dropdown = WaitUtil.waitForVisibility(driver,dropDownAccount,10);
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdown).perform();
        // Wait for and click Sign Out
        WebElement signOut = WaitUtil.waitForClickable(driver,logOut,10);
        signOut.click();
    }

    public String getSuccessMessage() {
        return WaitUtil.waitForVisibility(driver, successMessage, 10).getText();
    }

    public String getFirstNameError() {
        return getTextIfVisible(firstNameError);
    }

    public String getLastNameError() {
        return getTextIfVisible(lastNameError);
    }

    public String getEmailError() {
        return getTextIfVisible(emailError);
    }

    public String getPasswordError() {
        return getTextIfVisible(passwordError);
    }

    public String getConfirmPasswordError() {
        return getTextIfVisible(confirmPasswordError);
    }

    public String getGeneralError() {
        return getTextIfVisible(generalError);
    }

    private String getTextIfVisible(WebElement element) {
        try {
            return WaitUtil.waitForVisibility(driver, element, 3).getText();  // 3s timeout for field errors
        } catch (Exception e) {
            return "";
        }
    }

    public List<String> getAllValidationErrors() {
        List<String> errors = new ArrayList<>();

        if (!getFirstNameError().isBlank()) errors.add(getFirstNameError());
        if (!getLastNameError().isBlank()) errors.add(getLastNameError());
        if (!getEmailError().isBlank()) errors.add(getEmailError());
        if (!getPasswordError().isBlank()) errors.add(getPasswordError());
        if (!getConfirmPasswordError().isBlank()) errors.add(getConfirmPasswordError());
        if (!getGeneralError().isBlank()) errors.add(getGeneralError());

        return errors;
    }
}
