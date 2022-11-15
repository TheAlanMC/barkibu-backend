package bo.edu.ucb.barkibu.dto;

public class BreedDto {
    private Integer breedId;
    private String breed;

    public BreedDto() {
    }

    public BreedDto(Integer breedId, String breed) {
        this.breedId = breedId;
        this.breed = breed;
    }

    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
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
                ", breed='" + breed + '\'' +
                '}';
    }
}
