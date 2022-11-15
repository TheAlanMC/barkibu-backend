package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class Breed {
    private Integer breedId;
    private Integer speciesId;
    private String breed;
    private String status;
    private Date txDate;
    private String txUser;
    private Date txHost;

    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
    }

    public Integer getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Integer speciesId) {
        this.speciesId = speciesId;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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

    public Date getTxHost() {
        return txHost;
    }

    public void setTxHost(Date txHost) {
        this.txHost = txHost;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "breedId=" + breedId +
                ", speciesId=" + speciesId +
                ", breed='" + breed + '\'' +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost=" + txHost +
                '}';
    }
}
