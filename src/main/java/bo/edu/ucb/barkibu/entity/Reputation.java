package bo.edu.ucb.barkibu.entity;

public class Reputation {
    private Integer totalAnswers;
    private Integer totalPetOwnerLike;
    private Integer totalVeterinarianLike;

    public Reputation() {
    }

    public Reputation(Integer totalAnswers, Integer totalPetOwnerLike, Integer totalVeterinarianLike) {
        this.totalAnswers = totalAnswers;
        this.totalPetOwnerLike = totalPetOwnerLike;
        this.totalVeterinarianLike = totalVeterinarianLike;
    }

    public Integer getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(Integer totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    public Integer getTotalPetOwnerLike() {
        return totalPetOwnerLike;
    }

    public void setTotalPetOwnerLike(Integer totalPetOwnerLike) {
        this.totalPetOwnerLike = totalPetOwnerLike;
    }

    public Integer getTotalVeterinarianLike() {
        return totalVeterinarianLike;
    }

    public void setTotalVeterinarianLike(Integer totalVeterinarianLike) {
        this.totalVeterinarianLike = totalVeterinarianLike;
    }

    @Override
    public String toString() {
        return "Reputation{" +
                "totalAnswers=" + totalAnswers +
                ", totalPetOwnerLike=" + totalPetOwnerLike +
                ", totalVeterinarianLike=" + totalVeterinarianLike +
                '}';
    }
}
