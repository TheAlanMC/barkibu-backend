package bo.edu.ucb.barkibu.dto;

public class QuestionVeterinarianFilterDto {

    private String categoryId;
    private String specieId;
    private String answered;


    public QuestionVeterinarianFilterDto() {
    }

    public QuestionVeterinarianFilterDto(String categoryId, String specieId, String answered) {
        this.categoryId = categoryId;
        this.specieId = specieId;
        this.answered = answered;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSpecieId() {
        return specieId;
    }

    public void setSpecieId(String specieId) {
        this.specieId = specieId;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    @Override
    public String toString() {
        return "QuestionVeterinarianFilterDto{" +
                "categoryId='" + categoryId + '\'' +
                ", specieId='" + specieId + '\'' +
                ", answered=" + answered +
                '}';
    }

    public boolean validate() {
        if(categoryId == null){
            return false;
        }
        if(specieId == null){
            return false;
        }
        if(answered == null){
            return false;
        }
        return true;
    }
}
