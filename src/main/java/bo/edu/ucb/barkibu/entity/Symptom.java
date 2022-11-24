package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class Symptom {
    private Integer symptomId;
    private String symptom;
    private String status;
    private Date txDate;
    private String txUser;
    private Date txHost;

    public Symptom() {
    }

    public Symptom(Integer symptomId, String symptom, String status, Date txDate, String txUser, Date txHost) {
        this.symptomId = symptomId;
        this.symptom = symptom;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Integer symptomId) {
        this.symptomId = symptomId;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
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
        return "Symptom{" +
                "symptomId=" + symptomId +
                ", symptom='" + symptom + '\'' +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost=" + txHost +
                '}';
    }
}
