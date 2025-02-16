package org.example.websitekinhdoanhpc_casestudy_module3.entity;

public class Category {
    private Integer category_id;
    private String category_name;
    private String description;

    // Constructors, getters, and setters
    public Category() {
    }

    public Category(Integer category_id) {
        this.category_id = category_id;
    }

    public Category(Integer category_id, String category_name, String description) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.description = description;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
