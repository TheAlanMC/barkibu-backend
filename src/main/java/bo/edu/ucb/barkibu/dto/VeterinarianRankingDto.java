package bo.edu.ucb.barkibu.dto;

public class VeterinarianRankingDto {
    private Integer monthlyRanking;
    private Integer generalRanking;

    public VeterinarianRankingDto() {
    }

    public VeterinarianRankingDto(Integer monthlyRanking, Integer generalRanking) {
        this.monthlyRanking = monthlyRanking;
        this.generalRanking = generalRanking;
    }

    public Integer getMonthlyRanking() {
        return monthlyRanking;
    }

    public void setMonthlyRanking(Integer monthlyRanking) {
        this.monthlyRanking = monthlyRanking;
    }

    public Integer getGeneralRanking() {
        return generalRanking;
    }

    public void setGeneralRanking(Integer generalRanking) {
        this.generalRanking = generalRanking;
    }

    @Override
    public String toString() {
        return "RankingDto{" +
                "monthlyRanking=" + monthlyRanking +
                ", generalRanking=" + generalRanking +
                '}';
    }
}
