package org.example.websitekinhdoanhpc_casestudy_module3.service.impl;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.OrderItemRepository;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderItemService;

import java.util.List;

public class OrderItemService implements IOrderItemService {
    private static OrderItemRepository orderItemRepository = new OrderItemRepository();
    @Override
    public List<OrderItem> getAll() {
        return orderItemRepository.findAllOf1Order();
    }
    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
    @Override
    public void remove(int id) {
        orderItemRepository.remove(id);
    }
    @Override
    public void create(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
    @Override
    public void update(int id, OrderItem orderItem) {

    }


}
