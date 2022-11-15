package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class QuestionOwner {
    private Integer questionId;
    private String photoPath;
    private String problem;
    private String detailedDescription;
    private Date questionDate;

    public QuestionOwner() {
    }

    public QuestionOwner(Integer questionId, String photoPath, String problem, String detailedDescription, Date questionDate) {
        this.questionId = questionId;
        this.photoPath = photoPath;
        this.problem = problem;
        this.detailedDescription = detailedDescription;
        this.questionDate = questionDate;
    }

    public Integer getQuestionId() {return questionId;}

    public void setQuestionId(Integer questionId) {this.questionId = questionId;}

    public String getPhotoPath() {return photoPath;}

    public void setPhotoPath(String photoPath) {this.photoPath = photoPath;}

    public String getProblem() {return problem;}

    public void setProblem(String problem) {this.problem = problem;}

    public String getDetailedDescription() {return detailedDescription;}

    public void setDetailedDescription(String detailedDescription) {this.detailedDescription = detailedDescription;}



    public Date getQuestionDate() {return questionDate;}

    public void setQuestionDate(Date questionDate) {this.questionDate = questionDate;}

    @Override
    public String toString() {
        return "QuestionOwner{" +
                "questionId=" + questionId +
                ", photoPath='" + photoPath + '\'' +
                ", problem='" + problem + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", questionDate=" + questionDate +
                '}';
    }
}

