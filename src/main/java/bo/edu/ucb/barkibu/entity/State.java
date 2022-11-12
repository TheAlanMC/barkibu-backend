package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class State {
    private Integer stateId;
    private Integer countryId;
    private String state;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public State() {
    }

    public State(Integer stateId, Integer countryId, String state, String status, Date txDate, String txUser, String txHost) {
        this.stateId = stateId;
        this.countryId = countryId;
        this.state = state;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Override
    public String toString() {
        return "State{" +
                "stateId=" + stateId +
                ", countryId=" + countryId +
                ", state='" + state + '\'' +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                '}';
    }
}
