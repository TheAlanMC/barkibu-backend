package bo.edu.ucb.barkibu.entity;

import java.util.Date;
import java.util.Objects;

public class User {
    private Integer userId;
    private Integer cityId;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String photoPath;
    private String description;
    private Integer failedLoginAttempts;
    private Date failedLoginTime;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public User() {
    }
    

    public User(Integer userId, Integer cityId, String firstName, String lastName, String email, String userName, String password, String photoPath, String description, Integer failedLoginAttempts, Date failedLoginTime, String status, Date txDate, String txUser, String txHost) {
        this.userId = userId;
        this.cityId = cityId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.photoPath = photoPath;
        this.description = description;
        this.failedLoginAttempts = failedLoginAttempts;
        this.failedLoginTime = failedLoginTime;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public Date getFailedLoginTime() {
        return failedLoginTime;
    }

    public void setFailedLoginTime(Date failedLoginTime) {
        this.failedLoginTime = failedLoginTime;
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
        return "User{" +
                "userId=" + userId +
                ", cityId=" + cityId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", description='" + description + '\'' +
                ", failedLoginAttempts=" + failedLoginAttempts +
                ", failedLoginTime=" + failedLoginTime +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, cityId, firstName, lastName, email, password, photoPath, description, failedLoginAttempts, failedLoginTime, status, txDate, txUser, txHost);
    }
}
