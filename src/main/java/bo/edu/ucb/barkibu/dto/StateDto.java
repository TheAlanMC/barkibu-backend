package bo.edu.ucb.barkibu.dto;

public class StateDto {
    Integer stateId;
    String state;
    Integer countryId;

    public StateDto() {
    }

    public StateDto(Integer stateId, String state, Integer countryId) {
        this.stateId = stateId;
        this.state = state;
        this.countryId = countryId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "StateDto{" +
                "stateId=" + stateId +
                ", state='" + state + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
