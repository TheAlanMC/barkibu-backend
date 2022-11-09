package bo.edu.ucb.barkibu.dto;

public class UserDto {
    private Integer userId;
    private Integer cityId;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String photoPath;
    private String description;

    public UserDto() {
    }

    public UserDto(Integer userId, Integer cityId, String firstName, String lastName, String email, String userName, String photoPath, String description) {
        this.userId = userId;
        this.cityId = cityId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.photoPath = photoPath;
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", cityId=" + cityId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
