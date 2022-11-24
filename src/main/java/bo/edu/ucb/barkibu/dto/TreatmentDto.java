package bo.edu.ucb.barkibu.dto;

public class TreatmentDto {
    private Integer treatmentId;
    private String treatment;
    private String description;

    public TreatmentDto() {
    }

    public TreatmentDto(Integer treatmentId, String treatment, String description) {
        this.treatmentId = treatmentId;
        this.treatment = treatment;
        this.description = description;
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

    @Override
    public String toString() {
        return "TreatmentDto{" +
                "treatmentId=" + treatmentId +
                ", treatment='" + treatment + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
