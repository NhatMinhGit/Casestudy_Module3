package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.OrderRepository;

import java.io.IOException;

@WebServlet("/createOrder")
public class OrderController extends HttpServlet {
    private OrderRepository orderRepository = new OrderRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // Lấy session hiện tại
        User user = (User) session.getAttribute("user");
        Order newOrder = orderRepository.createOrder(user);
        if (newOrder != null) {
            response.getWriter().write("Đơn hàng đã được tạo thành công với ID: " + newOrder.getOrder_id());
        } else {
            response.getWriter().write("Lỗi khi tạo đơn hàng!");
        }
    }
}
