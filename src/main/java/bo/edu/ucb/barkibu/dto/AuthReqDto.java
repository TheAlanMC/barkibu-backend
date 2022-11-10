package bo.edu.ucb.barkibu.dto;

public class AuthReqDto{
    private String userName;
    private String password;

    public AuthReqDto() {
    }

    public AuthReqDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
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

    @Override
    public String toString() {
        return "AuthReqDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean validate() {
        if(this.userName == null || this.userName.isEmpty())  {
            return false;
        }
        if(this.password == null || this.password.isEmpty()) {
            return false;
        }
        return true;
    }
}
