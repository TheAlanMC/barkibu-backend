package bo.edu.ucb.barkibu.dto;

import java.util.Date;

public class AnswerDto {
    private Integer questionId;
    private String answer;
    private Date timeStamp;

    public AnswerDto() {
    }

    public AnswerDto(Integer questionId, String answer, Date timeStamp) {
        this.questionId = questionId;
        this.answer = answer;
        this.timeStamp = timeStamp;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "AnswerDto{" +
                "questionId=" + questionId +
                ", answer='" + answer + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }

    public boolean validateAnswer() {
        if(questionId == null) {
            return false;
        }
        if(this.answer == null || this.answer.isEmpty()) {
            return false;
        }
        if(this.timeStamp == null) {
            return false;
        }
        return true;
    }
}
