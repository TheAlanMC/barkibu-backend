package bo.edu.ucb.barkibu.dto;

public class SymptomDto {
    private Integer symptomId;
    private String symptom;

    public SymptomDto() {
    }

    public SymptomDto(Integer symptomId, String symptom) {
        this.symptomId = symptomId;
        this.symptom = symptom;
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

    @Override
    public String toString() {
        return "SymptomDto{" +
                "symptomId=" + symptomId +
                ", symptom='" + symptom + '\'' +
                '}';
    }
}
