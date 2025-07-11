package services;

import pageClass.CreateAccountPage;

public class CreateAccountService {

    private final CreateAccountPage createAccountPage;

    public CreateAccountService(CreateAccountPage createAccountPage) {
        this.createAccountPage = createAccountPage;
    }

    public void registerNewUser(String fname, String lname, String email, String password) {
        createAccountPage.fillForm(fname, lname, email, password);
        createAccountPage.submitForm();
    }
}
