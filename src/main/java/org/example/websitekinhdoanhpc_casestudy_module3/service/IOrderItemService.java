package org.example.websitekinhdoanhpc_casestudy_module3.service;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> getAll(int order_id);

    void save(OrderItem t);

    void remove(int id);

    void create(OrderItem t);

    void updateItemInCart(int order_id, OrderItem t);

    boolean addProductToOrder(int orderId, int productId);

    //Order findOrderById(int id);

    List<OrderItem> findOrderItemsByOrderId(int order_id);

    double calculateTotalPrice(int orderId);

//    void update(OrderItem item);
}
