package bo.edu.ucb.barkibu.dto;

import java.util.Date;

public class AnswerDto {
    private Integer questionId;
    private String answer;

    public AnswerDto() {
    }

    public AnswerDto(Integer questionId, String answer, Date timeStamp) {
        this.questionId = questionId;
        this.answer = answer;
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

    @Override
    public String toString() {
        return "AnswerDto{" +
                "questionId=" + questionId +
                ", answer='" + answer + '\'' +
                '}';
    }

    public boolean validate() {
        if(questionId == null) {
            return false;
        }
        if(this.answer == null || this.answer.isEmpty()) {
            return false;
        }
        return true;
    }
}
