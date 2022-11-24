package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class PetData {
    private String name;
    private String gender;
    private Integer breedId;
    private Integer specieId;
    private Boolean castrated;
    private Date bornDate;
    private String chipNumber;
    private  String photoPath;
    public PetData(){
    }

    public PetData(String name, String gender, Integer breedId, Integer specieId, Boolean castrated, Date bornDate, String chipNumber, String photoPath) {
        this.name = name;
        this.gender = gender;
        this.breedId = breedId;
        this.specieId = specieId;
        this.castrated = castrated;
        this.bornDate = bornDate;
        this.chipNumber = chipNumber;
        this.photoPath = photoPath;
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

    public Integer getSpecieId() {
        return specieId;
    }

    public void setSpecieId(Integer specieId) {
        this.specieId = specieId;
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "PetData{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", breedId=" + breedId +
                ", specieId=" + specieId +
                ", castrated=" + castrated +
                ", bornDate=" + bornDate +
                ", chipNumber='" + chipNumber + '\'' +
                ", photoPath='" + photoPath + '\'' +
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
        if (breedId == null || breedId == 0){
            return false;
        }
        if (castrated == null){
            return false;
        }
        if (bornDate == null){
            return false;
        }
          return true;
    }
}
