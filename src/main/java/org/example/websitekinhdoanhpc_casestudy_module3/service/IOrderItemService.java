package org.example.websitekinhdoanhpc_casestudy_module3.service;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> getAll();

    void save(OrderItem t);

    void remove(int id);

    void create(OrderItem t);

    void update(int id, OrderItem t);

    //Order findOrderById(int id);
}
