package bo.edu.ucb.barkibu.entity;

import java.util.Date;
import java.util.List;

public class PetQuestionInfo {
    private String specie;
    private String breed;
    private String gender;
    private Date bornDate;
    private Boolean castrated;

    public PetQuestionInfo() {
    }

    public PetQuestionInfo(String specie, String breed, String gender, Date bornDate, Boolean castrated, List<String> symptoms) {
        this.specie = specie;
        this.breed = breed;
        this.gender = gender;
        this.bornDate = bornDate;
        this.castrated = castrated;
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

    public Boolean getCastrated() {
        return castrated;
    }

    public void setCastrated(Boolean castrated) {
        this.castrated = castrated;
    }

    @Override
    public String toString() {
        return "PetQuestionInfo{" +
                "specie='" + specie + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", bornDate=" + bornDate +
                ", castrated=" + castrated +
                '}';
    }
}
