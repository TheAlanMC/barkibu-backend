package bo.edu.ucb.barkibu.dto;

import java.util.Date;
public class CreatePetDto {
    private Integer userId;
    private Integer breedId;
    private String name;
    private String gender;
    private Date bornDate;
    private String photoPath;

    public CreatePetDto() {
    }

    public CreatePetDto(Integer userId, Integer breedId, String name, String gender, Date bornDate, String photoPath) {
        this.userId = userId;
        this.breedId = breedId;
        this.name = name;
        this.gender = gender;
        this.bornDate = bornDate;
        this.photoPath = photoPath;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "CreatePetDto{" +
                "userId=" + userId +
                ", breedId=" + breedId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", bornDate='" + bornDate + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }

    public boolean validate () {
        if(userId == null) {
            return false;
        }
        if(breedId == null) {
            return false;
        }
        if(name == null || name.isEmpty()) {
            return false;
        }
        if(gender == null || gender.isEmpty()){
            return false;
        }
        if(bornDate == null) {
            return false;
        }
        if(photoPath == null || photoPath.isEmpty()) {
            return false;
        }
            return true;
    }
}
