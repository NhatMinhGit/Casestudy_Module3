package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.OrderItemService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutReviewServlet", value = "/checkout-review")
public class CheckoutReviewServlet extends HttpServlet {
    private OrderItemService orderItemService;

    @Override
    public void init() throws ServletException {
        orderItemService = new OrderItemService(); // Khởi tạo service nếu cần
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Lấy order từ session
        Order order = (Order) session.getAttribute("order");
        List<OrderItem> cart = null;

        if (order != null) {
            int orderId = order.getOrder_id();
            session.setAttribute("order_id", orderId); // Lưu order_id vào session
            System.out.println("doGet orderId cart " + orderId);
            cart = orderItemService.findOrderItemsByOrderId(orderId);
            session.setAttribute("cart", cart);
        } else {
            cart = (List<OrderItem>) session.getAttribute("cart");
        }

        req.setAttribute("cart", cart);

        // Log kiểm tra
        System.out.println("Cart content: " + cart);
        System.out.println("Debug: order_id stored in session = " + session.getAttribute("order_id"));

        // Chuyển đến trang review thanh toán
        req.getRequestDispatcher("/WEB-INF/view/product/checkout_review.jsp").forward(req, resp);
    }
}
