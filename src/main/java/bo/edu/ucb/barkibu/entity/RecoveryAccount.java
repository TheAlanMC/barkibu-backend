package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class RecoveryAccount {
    private Integer recoveryAccountId;
    private Integer userId;
    private String hashCode;
    private Date expirationDate;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public RecoveryAccount() {
    }

    public RecoveryAccount(Integer recoveryAccountId, Integer userId, String hashCode, Date expirationDate, String status, Date txDate, String txUser, String txHost) {
        this.recoveryAccountId = recoveryAccountId;
        this.userId = userId;
        this.hashCode = hashCode;
        this.expirationDate = expirationDate;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getRecoveryAccountId() {
        return recoveryAccountId;
    }

    public void setRecoveryAccountId(Integer recoveryAccountId) {
        this.recoveryAccountId = recoveryAccountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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
        return "RecoveryAccount{" +
                "recoveryAccountId=" + recoveryAccountId +
                ", userId=" + userId +
                ", hashCode='" + hashCode + '\'' +
                ", expirationDate=" + expirationDate +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                '}';
    }
}
