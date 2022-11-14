package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class Category {
    private Integer categoryId;
    private String category;
    private String status;
    private Date txDate;
    private String txUser;
    private Date txHost;

    public Category() {
    }

    public Category(Integer categoryId, String category, String status, Date txDate, String txUser, Date txHost) {
        this.categoryId = categoryId;
        this.category = category;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Date getTxHost() {
        return txHost;
    }

    public void setTxHost(Date txHost) {
        this.txHost = txHost;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost=" + txHost +
                '}';
    }
}
