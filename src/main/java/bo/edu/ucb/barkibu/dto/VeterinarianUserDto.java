package bo.edu.ucb.barkibu.dto;

public class VeterinarianUserDto {
    private String firstName;
    private String lastName;
    private Integer cityId;
    private Integer stateId;
    private Integer countryId;
    private String userName;
    private String email;
    private String description;
    private String photoPath;

    public VeterinarianUserDto() {
    }

    public VeterinarianUserDto(String firstName, String lastName, Integer cityId, Integer stateId, Integer countryId, String userName, String email, String description, String photoPath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityId = cityId;
        this.stateId = stateId;
        this.countryId = countryId;
        this.userName = userName;
        this.email = email;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
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
        return "EditVeterinarianDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cityId=" + cityId +
                ", stateId=" + stateId +
                ", countryId=" + countryId +
                ", username='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }

    public boolean validate() {
        if (firstName == null || firstName.isEmpty()) {
            return false;
        }
        if (lastName == null || lastName.isEmpty()) {
            return false;
        }
        if (cityId == null || cityId < 1) {
            return false;
        }
        if (userName == null || userName.isEmpty()) {
            return false;
        }
        if (email == null || email.isEmpty()) {
            return false;
        }
        if (description == null || description.isEmpty()) {
            return false;
        }
        if (photoPath == null || photoPath.isEmpty()) {
            return false;
        }
        return true;
    }
}
