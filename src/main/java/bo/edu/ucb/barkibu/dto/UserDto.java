package bo.edu.ucb.barkibu.dto;

public class UserDto {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String userName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
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
