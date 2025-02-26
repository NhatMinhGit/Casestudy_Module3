package org.example.websitekinhdoanhpc_casestudy_module3.service.impl;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.OrderRepository;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderItemService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderService;

import java.util.List;

public class OrderService implements IOrderService {
    private static OrderRepository orderRepository = new OrderRepository();
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(User user) {
        return orderRepository.createOrder(user);
    }

    @Override
    public void create(Order order) {orderRepository.save(order);}

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void remove(int id) {
        orderRepository.remove(id);
    }

    @Override
    public void update(int id, Order order) {
        orderRepository.update(id, order);
    }
    @Override
    public Order findOrderById(int id) {
        return orderRepository.findOrderById(id);
    }

}
