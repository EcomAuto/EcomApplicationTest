package models;

public class UserModel {
	private String useCase;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserModel(String useCase,String firstName, String lastName, String email, String password) {
    	this.useCase = useCase;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public String getUseCase() { return useCase; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Optionally add toString()
    @Override
    public String toString() {
        return useCase+" : "+firstName + " " + lastName + " (" + email + ")";
    }
}
