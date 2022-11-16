package bo.edu.ucb.barkibu.dto;

public class UpdateUserDto {
    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    public UpdateUserDto() {
    }

    public UpdateUserDto(String firstName, String lastName, String email, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;

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



    @Override
    public String toString() {
        return "CreateUserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
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

        return true;
    }
}
