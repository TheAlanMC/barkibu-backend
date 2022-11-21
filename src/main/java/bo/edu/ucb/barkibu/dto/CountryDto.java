package bo.edu.ucb.barkibu.dto;

public class CountryDto {
    Integer countryId;
    String country;

    public CountryDto() {
    }

    public CountryDto(Integer countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CountryDto{" +
                "countryId=" + countryId +
                ", country='" + country + '\'' +
                '}';
    }
}
