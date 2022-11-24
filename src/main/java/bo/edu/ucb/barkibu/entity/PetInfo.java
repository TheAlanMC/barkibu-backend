package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class PetInfo {
    private Integer petId;
    private String name;
    private String specie;
    private String breed;
    private String photoPath;
    private Date bornDate;
    private String chipNumber;
    private String gender;
    private Boolean castrated;

    public PetInfo(){
    }

    public PetInfo(Integer petId, String name, String specie, String breed, String photoPath, Date bornDate, String chip_number, String gender, Boolean castrated) {
        this.petId = petId;
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.photoPath = photoPath;
        this.bornDate = bornDate;
        this.chipNumber = chip_number;
        this.gender = gender;
        this.castrated = castrated;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getCastrated() {
        return castrated;
    }

    public void setCastrated(Boolean castrated) {
        this.castrated = castrated;
    }

    @Override
    public String toString() {
        return "PetInfo{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", specie='" + specie + '\'' +
                ", breed='" + breed + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", bornDate=" + bornDate +
                ", chipNumber='" + chipNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", castrated=" + castrated +
                '}';
    }
}
