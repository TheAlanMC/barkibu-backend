package bo.edu.ucb.barkibu.dto;

import java.util.Date;
import java.util.List;

public class PetInfoDto {
    private String specie;
    private String breed;
    private String gender;
    private Date bornDate;
    private boolean castrated;
    private List<String> symptoms;

    public PetInfoDto() {
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

    public boolean isCastrated() {
        return castrated;
    }

    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    @Override
    public String toString() {
        return "PetInfoDto{" +
                "specie='" + specie + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", bornDate=" + bornDate + '\'' +
                ", castrated=" + castrated + '\'' +
                ", symptoms=" + symptoms +
                '}';
    }
}
