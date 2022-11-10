package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class Pet {
    private Integer petId;
    private Integer userId;
    private Integer breedId;
    private String name;
    private String gender;
    private boolean castrated;
    private Date bornDate;
    private String photoPath;
    private String chipNumber;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public Pet() {
    }

    public Pet(Integer petId, Integer userId, Integer breedId, String name, String gender, boolean castrated, Date bornDate, String photoPath, String chipNumber, String status, Date txDate, String txUser, String txHost) {
        this.petId = petId;
        this.userId = userId;
        this.breedId = breedId;
        this.name = name;
        this.gender = gender;
        this.castrated = castrated;
        this.bornDate = bornDate;
        this.photoPath = photoPath;
        this.chipNumber = chipNumber;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
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

    public boolean isCastrated() {
        return castrated;
    }

    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", userId=" + userId +
                ", breedId=" + breedId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", castrated=" + castrated +
                ", bornDate=" + bornDate +
                ", photoPath='" + photoPath + '\'' +
                ", chipNumber='" + chipNumber + '\'' +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                '}';
    }
}
