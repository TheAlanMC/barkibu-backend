package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class PetTreatmentList {
    private Integer treatmentId;
    private Integer petId;
    private String treatment;
    private Date treatmentLastDate;
    private Date treatmentNextDate;

    public PetTreatmentList() {
    }

    public PetTreatmentList(Integer treatmentId, Integer petId, String treatment, Date treatmentLastDate, Date treatmentNextDate) {
        this.treatmentId = treatmentId;
        this.petId = petId;
        this.treatment = treatment;
        this.treatmentLastDate = treatmentLastDate;
        this.treatmentNextDate = treatmentNextDate;
    }

    public Integer getTreatmentId() {return treatmentId;}

    public void setTreatmentId(Integer treatmentId) {this.treatmentId = treatmentId;}

    public Integer getPetId() {return petId;}

    public void setPetId(Integer petId) {this.petId = petId;}

    public String getTreatment() {return treatment;}

    public void setTreatment(String treatment) {this.treatment = treatment;}

    public Date getTreatmentLastDate() {return treatmentLastDate;}

    public void setTreatmentLastDate(Date treatmentLastDate) {this.treatmentLastDate = treatmentLastDate;}

    public Date getTreatmentNextDate() {return treatmentNextDate;}

    public void setTreatmentNextDate(Date treatmentNextDate) {this.treatmentNextDate = treatmentNextDate;}

    @Override
    public String toString() {
        return "PetTreatmentList{" +
                "treatmentId=" + treatmentId +
                ", petId=" + petId +
                ", treatment='" + treatment + '\'' +
                ", treatmentLastDate=" + treatmentLastDate +
                ", treatmentNextDate=" + treatmentNextDate +
                '}';
    }
}
