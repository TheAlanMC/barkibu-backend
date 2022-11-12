package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class Country {
    private Integer countryId;
    private String country;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public Country() {
    }

    public Country(Integer stateId, Integer countryId, String state, String status, Date txDate, String txUser, String txHost) {
        this.countryId = stateId;
        this.countryId = countryId;
        this.country = state;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
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
                "stateId=" + countryId +
                ", countryId=" + countryId +
                ", state='" + country + '\'' +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                '}';
    }
}
