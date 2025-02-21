package org.example.websitekinhdoanhpc_casestudy_module3.service;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Category;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    Order createOrder(String number);
    void save(Order t);

    void remove(int id);

    void create(Order t);

    void update(int id, Order t);

    Order findOrderById(int id);
}
