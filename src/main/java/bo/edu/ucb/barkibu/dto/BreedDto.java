package bo.edu.ucb.barkibu.dto;

public class BreedDto {
    private Integer breedId;
    private Integer specieId;
    private String breed;

    public BreedDto() {
    }

    public BreedDto(Integer breedId, Integer specieId, String breed) {
        this.breedId = breedId;
        this.specieId = specieId;
        this.breed = breed;
    }

    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
    }

    public Integer getSpecieId() {
        return specieId;
    }

    public void setSpecieId(Integer specieId) {
        this.specieId = specieId;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "BreedDto{" +
                "breedId=" + breedId +
                ", specieId=" + specieId +
                ", breed='" + breed + '\'' +
                '}';
    }
}
