package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class VeterinarianOwnAnswer {
    private String petName;
    private String photoPath;
    private String question;
    private String answer;
    private Integer totalLikes;
    private Date answerDate;

    public VeterinarianOwnAnswer() {
    }

    public VeterinarianOwnAnswer(String petName, String photoPath, String question, String answer, Integer totalLikes, Date answerDate) {
        this.petName = petName;
        this.photoPath = photoPath;
        this.question = question;
        this.answer = answer;
        this.totalLikes = totalLikes;
        this.answerDate = answerDate;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    @Override
    public String toString() {
        return "VeterinarianOwnAnswers{" +
                "petName='" + petName + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", totalLikes=" + totalLikes +
                ", answerDate=" + answerDate +
                '}';
    }
}
