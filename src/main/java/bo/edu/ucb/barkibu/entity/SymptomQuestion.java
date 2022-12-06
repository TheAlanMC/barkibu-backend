package bo.edu.ucb.barkibu.entity;

import java.util.List;

public class SymptomQuestion {
    private Integer petId;
    private String problem;
    private String detailedDescription;
    private Integer categoryId;
    private List<Integer> symptomIdList;

    public SymptomQuestion() {
    }

    public SymptomQuestion(Integer petId, String problem, String detailedDescription, Integer categoryId, List<Integer> symptomIdList) {
        this.petId = petId;
        this.problem = problem;
        this.detailedDescription = detailedDescription;
        this.categoryId = categoryId;
        this.symptomIdList = symptomIdList;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getSymptomIdList() {
        return symptomIdList;
    }

    public void setSymptomIdList(List<Integer> symptomIdList) {
        this.symptomIdList = symptomIdList;
    }

    @Override
    public String toString() {
        return "SymptomQuestion{" +
                "petId=" + petId +
                ", problem='" + problem + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", categoryId=" + categoryId +
                ", symptomIdList=" + symptomIdList +
                '}';
    }

    public  boolean validate () {
        if (petId == null || petId<1) {
            return false;
        }
        if (problem == null || problem.trim().length() < 1) {
            return false;
        }
        if (detailedDescription == null || detailedDescription.trim().length() < 1) {
            return false;
        }
        if (categoryId == null || categoryId<1) {
            return false;
        }
        if (symptomIdList == null || symptomIdList.size() < 1) {
            return false;
        }
        return true;
    }
}
