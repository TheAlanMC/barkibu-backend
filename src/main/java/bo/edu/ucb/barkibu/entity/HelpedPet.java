package bo.edu.ucb.barkibu.entity;

public class HelpedPet {
    private Integer totalAnswers;
    private String specie;

    public HelpedPet() {
    }

    public HelpedPet(Integer totalAnswers, String specie) {
        this.totalAnswers = totalAnswers;
        this.specie = specie;
    }

    public Integer getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(Integer totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    @Override
    public String toString() {
        return "HelpedPet{" +
                "totalAnswers=" + totalAnswers +
                ", specie='" + specie + '\'' +
                '}';
    }
}
