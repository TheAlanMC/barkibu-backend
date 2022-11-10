package bo.edu.ucb.barkibu.dto;

public class RecoveryAccountReqDto {
    private String email;

    public RecoveryAccountReqDto() {
    }

    public RecoveryAccountReqDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RecoverAccountReqDto{" +
                "email='" + email + '\'' +
                '}';
    }

    public boolean validate() {
        if(this.email == null || this.email.isEmpty()) {
            return false;
        }
        return true;
    }
}
