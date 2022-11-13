package bo.edu.ucb.barkibu.dto;

public class UpdatePasswordDto {
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;

    public UpdatePasswordDto() {
    }

    public UpdatePasswordDto(String currentPassword, String newPassword, String confirmNewPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    @Override
    public String toString() {
        return "UpdatePasswordDto{" +
                "currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmNewPassword='" + confirmNewPassword + '\'' +
                '}';
    }

    public boolean validate() {
        if(this.currentPassword == null || this.currentPassword.isEmpty()) {
            return false;
        }
        if(this.newPassword == null || this.newPassword.isEmpty()) {
            return false;
        }
        if(this.confirmNewPassword == null || this.confirmNewPassword.isEmpty()) {
            return false;
        }
        return true;
    }
}
