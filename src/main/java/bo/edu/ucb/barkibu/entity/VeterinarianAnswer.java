package bo.edu.ucb.barkibu.entity;

public class VeterinarianAnswer {
    private Integer answerId;
    private Boolean liked;
    private Boolean answered;
    private String veterinarianName;
    private String veterinarianLastName;
    private String answer;
    private Integer totalLikes;
    private String answerDate;

    public VeterinarianAnswer() {
    }

    public VeterinarianAnswer(Integer answerId, Boolean liked, Boolean answered, String veterinarianName, String veterinarianLastName, String answer, Integer totalLikes, String answerDate) {
        this.answerId = answerId;
        this.liked = liked;
        this.answered = answered;
        this.veterinarianName = veterinarianName;
        this.veterinarianLastName = veterinarianLastName;
        this.answer = answer;
        this.totalLikes = totalLikes;
        this.answerDate = answerDate;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public String getVeterinarianName() {
        return veterinarianName;
    }

    public void setVeterinarianName(String veterinarianName) {
        this.veterinarianName = veterinarianName;
    }

    public String getVeterinarianLastName() {
        return veterinarianLastName;
    }

    public void setVeterinarianLastName(String veterinarianLastName) {
        this.veterinarianLastName = veterinarianLastName;
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

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }

    @Override
    public String toString() {
        return "VeterinarianAnswer{" +
                "answerId=" + answerId +
                ", liked=" + liked +
                ", answered=" + answered +
                ", veterinarianName='" + veterinarianName + '\'' +
                ", veterinarianLastName='" + veterinarianLastName + '\'' +
                ", answer='" + answer + '\'' +
                ", totalLikes=" + totalLikes +
                ", answerDate='" + answerDate + '\'' +
                '}';
    }
}
