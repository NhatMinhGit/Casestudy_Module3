package org.example.websitekinhdoanhpc_casestudy_module3.service.impl;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.OrderItemRepository;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderItemService;

import java.util.List;

public class OrderItemService implements IOrderItemService {
    private static OrderItemRepository orderItemRepository = new OrderItemRepository();
    @Override
    public List<OrderItem> getAll(int order_id) {
        return orderItemRepository.findAllOf1Order(order_id);
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
    public void updateItemInCart(int order_id, OrderItem orderItem) {
        orderItemRepository.updateItemInCart(order_id, orderItem);
    }


    @Override
    public boolean addProductToOrder(int orderId, int productId) {
        if (orderId == 0 || orderId <= 0 || productId <= 0) {
            System.out.println("Dữ liệu đầu vào không hợp lệ!");
            return false;
        }
        return orderItemRepository.addProductToOrder(orderId, productId);
    }
}
