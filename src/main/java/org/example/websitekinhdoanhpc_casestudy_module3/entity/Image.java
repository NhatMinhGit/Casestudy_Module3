package org.example.websitekinhdoanhpc_casestudy_module3.entity;

public class Image {
    private Integer image_id;
    private Product product;
    private String image_url;
    private String image_name;

    // Constructors, getters, and setters
    public Image() {
    }

    public Image(Integer image_id, Product product, String image_url, String image_name) {
        this.image_id = image_id;
        this.product = product;
        this.image_url = image_url;
        this.image_name = image_name;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    @Override
    public String toString() {
        return "Image{" +
                "image_id=" + image_id +
                ", product=" + product +
                ", image_url='" + image_url + '\'' +
                ", image_name='" + image_name + '\'' +
                '}';
    }
}
