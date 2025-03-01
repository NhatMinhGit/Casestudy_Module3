package org.example.websitekinhdoanhpc_casestudy_module3.service;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Category;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    List<Order> findOrdersByUserId(int id);
    Order createOrder(User user);
    void save(Order t);

    void remove(int id);

    void create(Order t);

    void update(int id, Order t);

    Order findOrderById(int id);
    boolean updateOrderStatus(int orderId, String status);
    Order findLastInsertedOrder();

    void updateTotalPrice(int orderId, double totalPrice);
}
