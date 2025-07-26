package models;

public class InvalidLoginModel {
    private String email;
    private String password;
    private String expectedError;

    public InvalidLoginModel(String email, String password, String expectedError) {
        this.email = email;
        this.password = password;
        this.expectedError = expectedError;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedError() {
        return expectedError;
    }
}
