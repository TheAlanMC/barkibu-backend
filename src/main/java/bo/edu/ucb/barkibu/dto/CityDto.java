package bo.edu.ucb.barkibu.dto;

public class CityDto {
    Integer cityId;
    String city;
    Integer stateId;

    public CityDto() {
    }

    public CityDto(Integer cityId, String city, Integer stateId) {
        this.cityId = cityId;
        this.city = city;
        this.stateId = stateId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "CityDto{" +
                "cityId=" + cityId +
                ", city='" + city + '\'' +
                ", stateId=" + stateId +
                '}';
    }
}
