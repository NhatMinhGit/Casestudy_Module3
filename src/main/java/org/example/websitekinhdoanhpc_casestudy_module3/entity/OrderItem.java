package org.example.websitekinhdoanhpc_casestudy_module3.entity;

import java.util.Objects;

public class OrderItem {
    private String order_item_id;
    private Order order;
    private Product product;
    private Integer quantity;
    private Double price_per_unit;

    // Constructors, getters, and setters

    public OrderItem() {
    }

    public OrderItem(String order_item_id, Order order, Product product, Integer quantity, Double price_per_unit) {
        this.order_item_id = order_item_id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price_per_unit = price_per_unit;
    }

    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(Double price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order_item_id='" + order_item_id + '\'' +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price_per_unit=" + price_per_unit +
                '}';
    }
}
