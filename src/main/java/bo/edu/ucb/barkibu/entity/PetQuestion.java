package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class PetQuestion{
    private Integer questionId;
    private String petName;
    private String photoPath;
    private String problem;
    private String description;
    private Date postedDate;

    public PetQuestion() {
    }

    public PetQuestion(Integer questionId, String petName, String photoPath, String problem, String description,Date postedDate) {
        this.questionId = questionId;
        this.petName = petName;
        this.photoPath = photoPath;
        this.problem = problem;
        this.description = description;
        this.postedDate = postedDate;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public String toString() {
        return "PetQuestion{" +
                "questionId=" + questionId + '\'' +
                ", petName='" + petName + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", problem='" + problem + '\'' +
                ", description='" + description + '\'' +
                ", postedDate=" + postedDate +
                '}';
    }
}
