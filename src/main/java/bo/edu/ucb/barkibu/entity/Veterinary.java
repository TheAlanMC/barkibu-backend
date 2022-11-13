package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class Veterinary {
    private Integer veterinaryId;
    private Integer userId;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private String description;
    private String status;
    private Date txDate;
    private String txUser;
    private Integer txHost;

    public Veterinary() {
    }

    public Veterinary(Integer veterinaryId, Integer userId, String name, String address, String latitude, String longitude, String description, String status, Date txDate, String txUser, Integer txHost) {
        this.veterinaryId = veterinaryId;
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getVeterinaryId() {
        return veterinaryId;
    }

    public void setVeterinaryId(Integer veterinaryId) {
        this.veterinaryId = veterinaryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getTxHost() {
        return txHost;
    }

    public void setTxHost(Integer txHost) {
        this.txHost = txHost;
    }

    @Override
    public String toString() {
        return "Veterinary{" +
                "veterinaryId=" + veterinaryId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost=" + txHost +
                '}';
    }
}
