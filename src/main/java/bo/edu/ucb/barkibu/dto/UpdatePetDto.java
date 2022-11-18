package bo.edu.ucb.barkibu.dto;

import java.util.Date;

public class UpdatePetDto {
    private String name;
    private String gender;
    private Integer breedId;
    private Boolean castrated;
    private Date bornDate;
    private String chipNumber;
    public UpdatePetDto(){
    }

    public UpdatePetDto(String name, String gender, Integer breedId, Boolean castrated, Date bornDate, String chipNumber) {
        this.name = name;
        this.gender = gender;
        this.breedId = breedId;
        this.castrated = castrated;
        this.bornDate = bornDate;
        this.chipNumber = chipNumber;
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

    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
    }

    public Boolean getCastrated() {
        return castrated;
    }

    public void setCastrated(Boolean castrated) {
        this.castrated = castrated;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    @Override
    public String toString() {
        return "UpdatePetDto{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", breedId=" + breedId +
                ", castrated=" + castrated +
                ", bornDate=" + bornDate +
                ", chipNumber='" + chipNumber + '\'' +
                '}';
    }

   //Revisar
    public boolean validate() {
        if (name == null || name.isEmpty()){
            return false;
        }
        if (gender == null || gender.isEmpty()){
            return false;
        }
        if (chipNumber == null || chipNumber.isEmpty()){
            return false;
        }

        return true;
    }

}

