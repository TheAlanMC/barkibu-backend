package bo.edu.ucb.barkibu.dto;

public class RecoveryPasswordDto {
    private String email;
    private String hashCode;
    private String password;
    private String confirmPassword;

    public RecoveryPasswordDto() {
    }

    public RecoveryPasswordDto(String email, String code, String password, String confirmPassword) {
        this.email = email;
        this.hashCode = code;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
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
        return "RecoveryPasswordDto{" +
                "email='" + email + '\'' +
                ", code='" + hashCode + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }

    public boolean validate() {
        if(this.email == null || this.email.isEmpty() || this.hashCode == null || this.hashCode.isEmpty() || this.password == null || this.password.isEmpty() || this.confirmPassword == null || this.confirmPassword.isEmpty()) {
            return false;
        }
        return true;
    }
}
