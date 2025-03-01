package org.example.websitekinhdoanhpc_casestudy_module3.entity;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Product {
    private Integer product_id;
    private Category category;
    private String name;
    private Double price;
    private String image_url;
    private String description;
    private Integer stock_quantity;

    // Constructors, getters, and setters

    public Product(String titleProduct, Double priceProduct, String imgProduct) {
    }

    public Product(Integer product_id, Category category, String name, Double price, String image_url, String description, Integer stock_quantity) {
        this.product_id = product_id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.image_url = image_url;
        this.description = description;
        this.stock_quantity = stock_quantity;
    }

    public String getFormattedPrice() {
        if (price == null) {
            return "0 đ";
        }
        NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        return formatter.format(price) + " đ";
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(product_id, product.product_id) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", stock_quantity=" + stock_quantity +
                '}';
    }
}
