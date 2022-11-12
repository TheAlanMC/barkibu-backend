package bo.edu.ucb.barkibu.dto;

public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String confirmPassword;

    public CreateUserDto() {
    }

    public CreateUserDto(String firstName, String lastName, String email, String userName, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "CreateUserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }

    public boolean validate() {
        if (firstName == null || firstName.isEmpty()){
            return false;
        }
        if (lastName == null || lastName.isEmpty()){
            return false;
        }
        if (email == null || email.isEmpty()){
            return false;
        }
        if (userName == null || userName.isEmpty()){
            return false;
        }
        if (password == null || password.isEmpty()){
            return false;
        }
        if (confirmPassword == null || confirmPassword.isEmpty()){
            return false;
        }
        return true;
    }
}
