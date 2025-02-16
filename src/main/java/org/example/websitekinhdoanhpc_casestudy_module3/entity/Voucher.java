package org.example.websitekinhdoanhpc_casestudy_module3.entity;

import java.util.Objects;

public class Voucher {
    private String voucher_id;
    private Order order;
    private String title;
    private Float sale_amount;
    private String note;
    private String start_date;
    private String end_date;

    // Constructors, getters, and setters
    public Voucher() {
    }

    public Voucher(String voucher_id, Order order, String title, Float sale_amount, String note, String start_date, String end_date) {
        this.voucher_id = voucher_id;
        this.order = order;
        this.title = title;
        this.sale_amount = sale_amount;
        this.note = note;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getSale_amount() {
        return sale_amount;
    }

    public void setSale_amount(Float sale_amount) {
        this.sale_amount = sale_amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voucher voucher = (Voucher) o;
        return Objects.equals(voucher_id, voucher.voucher_id) && Objects.equals(order, voucher.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voucher_id, order);
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "voucher_id='" + voucher_id + '\'' +
                ", order=" + order +
                ", title='" + title + '\'' +
                ", sale_amount=" + sale_amount +
                ", note='" + note + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                '}';
    }
}
