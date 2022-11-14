package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class QuestionOwner {
    private Integer questionId;
    private Integer userId;
    private Integer categoryId;
    private Integer petId;
    private String problem;
    private String detailedDescription;
    private boolean Answered;
    private Date questionDate;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public QuestionOwner() {
    }

    public QuestionOwner(Integer questionId, Integer userId, Integer categoryId, Integer petId, String problem, String detailedDescription, boolean answered, Date questionDate, String status, Date txDate, String txUser, String txHost) {
        this.questionId = questionId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.petId = petId;
        this.problem = problem;
        this.detailedDescription = detailedDescription;
        Answered = answered;
        this.questionDate = questionDate;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }


    public Integer getQuestionId() {return questionId;}

    public void setQuestionId(Integer questionId) {this.questionId = questionId;}

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

    public boolean isAnswered() {return Answered;}

    public void setAnswered(boolean answered) {Answered = answered;}

    public Date getQuestionDate() {return questionDate;}

    public void setQuestionDate(Date questionDate) {this.questionDate = questionDate;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public Date getTxDate() {return txDate;}

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getTxUser() {return txUser;}

    public void setTxUser(String txUser) {this.txUser = txUser;}

    public String getTxHost() {return txHost;}

    public void setTxHost(String txHost) {this.txHost = txHost;}
}

