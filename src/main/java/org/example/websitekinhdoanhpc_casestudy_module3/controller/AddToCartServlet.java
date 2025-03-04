package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Product;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.OrderItemRepository;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private ProductRepository productRepository;

    @Override
    public void init() {
        productRepository = new ProductRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Product product = productRepository.findById(productId);

        if (product != null) {
            OrderItem newItem = new OrderItem(0, null, product, quantity, product.getPrice());

            if (userId != null) {
                // Người dùng đã đăng nhập -> Lưu vào DB
                OrderItemRepository orderItemRepository = new OrderItemRepository();
                orderItemRepository.save(newItem);
            } else {
                // Người dùng vãng lai -> Lưu vào session
                List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
                if (cart == null) {
                    cart = new ArrayList<>();
                }

                // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
                boolean found = false;
                for (OrderItem item : cart) {
                    if (item.getProduct().getProduct_id() == productId) {
                        item.setQuantity(item.getQuantity() + quantity);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    cart.add(newItem);
                }

                session.setAttribute("cart", cart);
            }
        }
        response.sendRedirect("cart.jsp"); // Chuyển hướng về trang giỏ hàng
    }
}
