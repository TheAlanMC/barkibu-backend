package bo.edu.ucb.barkibu.dto;

import java.util.Date;
public class CreatePetDto {
    private Integer userId;
    private Integer breedId;
    private String name;
    private String gender;
    private Date bornDate;
    private String photoPath;
    private String chipNumber;

    public CreatePetDto() {
    }

    public CreatePetDto(Integer userId, Integer breedId, String name, String gender, Date bornDate, String photoPath, String chipNumber) {
        this.userId = userId;
        this.breedId = breedId;
        this.name = name;
        this.gender = gender;
        this.bornDate = bornDate;
        this.photoPath = photoPath;
        this.chipNumber = chipNumber;
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

    public String getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
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
                ", chipNumber='" + chipNumber + '\'' +
                '}';
    }

    public boolean validate () {
        if(userId == null || breedId == null || name == null || name.isEmpty() || gender == null || gender.isEmpty() || bornDate == null || photoPath == null || photoPath.isEmpty() ||  chipNumber == null || chipNumber.isEmpty()) {
            return false;
        }
        return true;
    }
}
