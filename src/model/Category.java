package model;

// clasa care descrie categoria generala a unei activitati
public class Category extends BaseEntity {
    private String categoryName;
    private String categoryDescription;

    public Category() {
    }

    public Category(int id, String categoryName, String categoryDescription) {
        super(id);
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    // getter pentru numele categoriei
    public String getCategoryName() {
        return categoryName;
    }

    // setter pentru numele categoriei
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // getter pentru descrierea categoriei
    public String getCategoryDescription() {
        return categoryDescription;
    }

    // setter pentru descrierea categoriei
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + getId() +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}