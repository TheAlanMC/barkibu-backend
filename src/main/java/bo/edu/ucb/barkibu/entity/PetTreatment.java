package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class PetTreatment {
    private Integer petTreatmentId;
    private Integer treatmentId;
    private Integer petId;
    private Date treatmentLastDate;
    private Date treatmentNextDate;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public PetTreatment() {
    }

    public PetTreatment(Integer petTreatmentId, Integer treatmentId, Integer petId, Date treatmentLastDate, Date treatmentNextDate, String status, Date txDate, String txUser, String txHost) {
        this.petTreatmentId = petTreatmentId;
        this.treatmentId = treatmentId;
        this.petId = petId;
        this.treatmentLastDate = treatmentLastDate;
        this.treatmentNextDate = treatmentNextDate;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getPetTreatmentId() {
        return petTreatmentId;
    }

    public void setPetTreatmentId(Integer petTreatmentId) {
        this.petTreatmentId = petTreatmentId;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Date getTreatmentLastDate() {
        return treatmentLastDate;
    }

    public void setTreatmentLastDate(Date treatmentLastDate) {
        this.treatmentLastDate = treatmentLastDate;
    }

    public Date getTreatmentNextDate() {
        return treatmentNextDate;
    }

    public void setTreatmentNextDate(Date treatmentNextDate) {
        this.treatmentNextDate = treatmentNextDate;
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
        return "PetTreatment{" +
                "petTreatmentId=" + petTreatmentId +
                ", treatmentId=" + treatmentId +
                ", petId=" + petId +
                ", treatmentLastDate=" + treatmentLastDate +
                ", treatmentNextDate=" + treatmentNextDate +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                '}';
    }
}
