package bo.edu.ucb.barkibu.dto;

import java.util.Date;

public class CreateQuestionDto {
    private Integer userId;
    private Integer categoryId;
    private Integer petId;
    private String problem;
    private String detailedDescription;
    private boolean answered;
    private Date questionDate;

    public CreateQuestionDto() {
    }

    public CreateQuestionDto(Integer userId, Integer categoryId, Integer petId, String problem, String detailedDescription, boolean answered, Date questionDate) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.petId = petId;
        this.problem = problem;
        this.detailedDescription = detailedDescription;
        this.answered = answered;
        this.questionDate = questionDate;
    }

    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}

    public Integer getCategoryId() {return categoryId;}

    public void setCategoryId(Integer categoryId) {this.categoryId = categoryId;}

    public Integer getPetId() {return petId;}

    public void setPetId(Integer petId) {this.petId = petId;}

    public String getProblem() {return problem;}

    public void setProblem(String problem) {this.problem = problem;}

    public String getDetailedDescription() {return detailedDescription;}

    public void setDetailedDescription(String detailedDescription) {this.detailedDescription = detailedDescription;}

    public boolean isAnswered() {return answered;}

    public void setAnswered(boolean answered) {this.answered = answered;}

    public Date getQuestionDate() {return questionDate;}

    public void setQuestionDate(Date questionDate) {this.questionDate = questionDate;}

    @Override
    public String toString() {
        return "CreateQuestionDto{" +
                "userId=" + userId +
                ", categoryId=" + categoryId +
                ", petId=" + petId +
                ", problem='" + problem + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", answered=" + answered +
                ", questionDate=" + questionDate +
                '}';
    }
    public boolean validate() {
        if(userId == null  || petId == null || categoryId == null || problem == null ||
                detailedDescription == null ||  questionDate == null ) {
            return false;
        }
        return true;
    }
}
