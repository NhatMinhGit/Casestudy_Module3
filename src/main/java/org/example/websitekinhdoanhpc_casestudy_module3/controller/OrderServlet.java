package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.OrderItemRepository;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/checkout")
public class OrderServlet extends HttpServlet {
    private OrderItemRepository orderItemRepository;

    @Override
    public void init() throws ServletException {
        orderItemRepository = new OrderItemRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        List<OrderItem> cart;

        if (userId != null) {
            // Người dùng đã đăng nhập -> Lấy giỏ hàng từ DB
            cart = orderItemRepository.getCartItemsByUserId(userId);
        } else {
            // Người dùng vãng lai -> Lấy giỏ hàng từ session
            cart = (List<OrderItem>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }
        }

        session.setAttribute("cart", cart);
        request.getRequestDispatcher("webapp/WEB-INF/view/product/checkout_review.jsp").forward(request, response);
    }
}


