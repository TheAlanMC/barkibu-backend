package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class Treatment {
    private Integer treatmentId;
    private String treatment;
    private String description;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public Treatment() {
    }

    public Treatment(Integer treatmentId, String treatment, String description, String status, Date txDate, String txUser, String txHost) {
        this.treatmentId = treatmentId;
        this.treatment = treatment;
        this.description = description;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
