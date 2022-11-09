package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class Role {
    private Integer roleId;
    private String roleName;
    private String description;
    private String status;
    private Date txDate;
    private String txUser;
    private String txHost;

    public Role() {
    }

    public Role(Integer roleId, String roleName, String description, String status, Date txDate, String txUser, String txHost) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                '}';
    }
}

