package org.example.websitekinhdoanhpc_casestudy_module3.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private int order_id;
    private User user;
    private LocalDate order_date;
    private Double total_price;

    private String status;// có thể xài enum thay thế

    private String shipping_address;
    private String payment_method;

    // Constructors, getters, and setters

    public Order() {
    }

    public Order(int order_id, User user, LocalDate order_date, Double total_price, String status, String shipping_address, String payment_method) {
        this.order_id = order_id;
        this.user = user;
        this.order_date = order_date;
        this.total_price = total_price;
        this.status = status;
        this.shipping_address = shipping_address;
        this.payment_method = payment_method;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(order_id, order.order_id) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, user);
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", user=" + user +
                ", order_date=" + order_date +
                ", total_price=" + total_price +
                ", status='" + status + '\'' +
                ", shipping_address='" + shipping_address + '\'' +
                ", payment_method='" + payment_method + '\'' +
                '}';
    }


}
