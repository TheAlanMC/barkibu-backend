package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class PetInfoId {
    private String name;
    private String specie;
    private String breed;
    private Date bornDate;
    private String photoPath;
    private String chipNumber;
    public PetInfoId() {
    }

    public PetInfoId(String name, String specie, String breed, Date bornDate, String photoPath, String chipNumber) {
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.bornDate = bornDate;
        this.photoPath = photoPath;
        this.chipNumber = chipNumber;
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
        return "PetInfoId{" +
                "name='" + name + '\'' +
                ", specie='" + specie + '\'' +
                ", breed='" + breed + '\'' +
                ", bornDate=" + bornDate +
                ", photoPath='" + photoPath + '\'' +
                ", chipNumber='" + chipNumber + '\'' +
                '}';
    }
}
