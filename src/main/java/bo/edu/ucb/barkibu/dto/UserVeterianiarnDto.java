package bo.edu.ucb.barkibu.dto;

public class UserVeterianiarnDto {
    private String firstName;
    private String lastName;
    private String description;
    private String photoPath;

    public UserVeterianiarnDto() {
    }

    public UserVeterianiarnDto(String firstName, String lastName, String description, String photoPath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.photoPath = photoPath;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "UserVeterianiarnDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
