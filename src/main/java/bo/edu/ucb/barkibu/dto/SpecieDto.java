package bo.edu.ucb.barkibu.dto;

public class SpecieDto {
    private Integer specieId;
    private String specie;

    public SpecieDto() {
    }

    public SpecieDto(Integer specieId, String specie) {
        this.specieId = specieId;
        this.specie = specie;
    }

    public Integer getSpecieId() {
        return specieId;
    }

    public void setSpecieId(Integer specieId) {
        this.specieId = specieId;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    @Override
    public String toString() {
        return "SpecieDto{" +
                "specieId=" + specieId +
                ", specie='" + specie + '\'' +
                '}';
    }
}
