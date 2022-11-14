package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class Answer {
    private Integer answerId;
    private Integer questionId;
    private Integer userId;
    private String answer;
    private String timeStamp;
    private Integer petOwnerLike;
    private Integer veterinarianLike;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public Answer() {
    }

    public Answer(Integer answerId, Integer questionId, Integer userId, String answer, String timeStamp, Integer petOwnerLike, Integer veterinarianLike, String status, Date txDate, String txUser, String txHost) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.userId = userId;
        this.answer = answer;
        this.timeStamp = timeStamp;
        this.petOwnerLike = petOwnerLike;
        this.veterinarianLike = veterinarianLike;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getPetOwnerLike() {
        return petOwnerLike;
    }

    public void setPetOwnerLike(Integer petOwnerLike) {
        this.petOwnerLike = petOwnerLike;
    }

    public Integer getVeterinarianLike() {
        return veterinarianLike;
    }

    public void setVeterinarianLike(Integer veterinarianLike) {
        this.veterinarianLike = veterinarianLike;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", questionId=" + questionId +
                ", userId=" + userId +
                ", answer='" + answer + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", petOwnerLike=" + petOwnerLike +
                ", veterinarianLike=" + veterinarianLike +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                '}';
    }
}
