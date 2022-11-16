package bo.edu.ucb.barkibu.dto;

import java.util.Date;

public class PetOwnTreatmentListDto {
    private Integer petTreatmentId;
    private Integer petId;
    private Date treatmentLastDate;
    private Date treatmentNextDate;

    public PetOwnTreatmentListDto() {
    }

    public PetOwnTreatmentListDto(Integer petTreatmentId, Integer petId, Date treatmentLastDate, Date treatmentNextDate) {
        this.petTreatmentId = petTreatmentId;
        this.petId = petId;
        this.treatmentLastDate = treatmentLastDate;
        this.treatmentNextDate = treatmentNextDate;
    }

    public Integer getPetTreatment() {return petTreatmentId;}

    public void setPetTreatment(Integer petTreatmentId) {this.petTreatmentId = petTreatmentId;}

    public Integer getPetId() {return petId;}

    public void setPetId(Integer petId) {this.petId = petId;}

    public Date getTreatmentLastDate() {return treatmentLastDate;}

    public void setTreatmentLastDate(Date treatmentLastDate) {this.treatmentLastDate = treatmentLastDate;}

    public Date getTreatmentNextDate() {return treatmentNextDate;}

    public void setTreatmentNextDate(Date treatmentNextDate) {this.treatmentNextDate = treatmentNextDate;}

    @Override
    public String toString() {
        return "PetOwnTreatment{" +
                "petTreatment=" + petTreatmentId +
                ", petId=" + petId +
                ", treatmentLastDate=" + treatmentLastDate +
                ", treatmentNextDate=" + treatmentNextDate +
                '}';
    }
}
