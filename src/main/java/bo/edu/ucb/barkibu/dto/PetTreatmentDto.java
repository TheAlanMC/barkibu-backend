package bo.edu.ucb.barkibu.dto;

import java.util.Date;

public class PetTreatmentDto {
    private Integer treatmentId;
    private Integer petId;
    private Date treatmentLastDate;
    private Date treatmentNextDate;

    public PetTreatmentDto() {
    }

    public PetTreatmentDto(Integer treatmentId, Integer petId, Date treatmentLastDate, Date treatmentNextDate) {
        this.treatmentId = treatmentId;
        this.petId = petId;
        this.treatmentLastDate = treatmentLastDate;
        this.treatmentNextDate = treatmentNextDate;
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


    public boolean validate() {
        if(treatmentId == null || petId == null || treatmentLastDate == null || treatmentNextDate == null) {
            return false;
        }
    return true;
    }
}
