package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class City {
    private Integer cityId;
    private Integer stateId;
    private String city;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public City() {
    }

    public City(Integer cityId, Integer stateId, String city, String status, Date txDate, String txUser, String txHost) {
        this.cityId = cityId;
        this.stateId = stateId;
        this.city = city;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        return "City{" +
                "cityId=" + cityId +
                ", stateId=" + stateId +
                ", city='" + city + '\'' +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                '}';
    }
}
