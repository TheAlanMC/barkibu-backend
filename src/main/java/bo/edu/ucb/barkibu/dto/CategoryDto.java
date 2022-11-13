package bo.edu.ucb.barkibu.dto;

public class CategoryDto {
    private Integer categoryId;
    private String category;

    public CategoryDto() {
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

    @Override
    public String toString() {
        return "CategoryDto{" +
                "idCategory=" + categoryId +
                ", category='" + category + '\'' +
                '}';
    }
}
