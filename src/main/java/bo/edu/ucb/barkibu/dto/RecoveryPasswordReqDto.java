package bo.edu.ucb.barkibu.dto;

public class RecoveryPasswordReqDto {
    private String email;
    private String hashCode;

    public RecoveryPasswordReqDto() {
    }

    public RecoveryPasswordReqDto(String email, String hashCode) {
        this.email = email;
        this.hashCode = hashCode;
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

    @Override
    public String toString() {
        return "RecoveryPasswordReqDto{" +
                "email='" + email + '\'' +
                ", hashCode='" + hashCode + '\'' +
                '}';
    }

    public boolean validate() {
        if(this.email == null || this.email.isEmpty()) {
            return false;
        }
        if(this.hashCode == null || this.hashCode.isEmpty()) {
            return false;
        }
        return true;
    }
}
