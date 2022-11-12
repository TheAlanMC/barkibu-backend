package bo.edu.ucb.barkibu.dto;

import java.util.Date;

public class PetTreatmentDto {
    private Integer treatmentId;
    private Integer petId;
    private Date treatmentLastDate;

    public PetTreatmentDto() {
    }

    public PetTreatmentDto(Integer treatmentId, Integer petId, Date treatmentLastDate) {
        this.treatmentId = treatmentId;
        this.petId = petId;
        this.treatmentLastDate = treatmentLastDate;
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

    public boolean validate() {
        if(treatmentId == null || petId == null || treatmentLastDate == null) {
            return false;
        }
    return true;
    }
}
