package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.OrderService;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet("/Cart/remove")
public class CartRemoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Kiểm tra request parameter
        String productIdStr = request.getParameter("product_id");
        if (productIdStr == null || productIdStr.isEmpty()) {
            System.out.println(" Lỗi: Không nhận được product_id từ request!");
            response.sendRedirect("error.jsp");
            return;
        }

        Integer product_id = Integer.parseInt(productIdStr);
        System.out.println(" Nhận được product_id: " + product_id);

        // Kiểm tra giỏ hàng
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        if (cart == null) {
            System.out.println(" Lỗi: Giỏ hàng trong session bị null!");
            response.sendRedirect("error.jsp");
            return;
        }

        System.out.println("🛒 Giỏ hàng trước khi xóa:");
        for (OrderItem item : cart) {
            System.out.println("   🔹 ID: " + item.getProduct().getProduct_id());
        }

        boolean removed = false;
        Iterator<OrderItem> iterator = cart.iterator();
        while (iterator.hasNext()) {
            OrderItem item = iterator.next();
            if (item.getProduct().getProduct_id().equals(product_id)) {
                iterator.remove(); // Sử dụng iterator để tránh lỗi ConcurrentModificationException
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println(" Đã xóa sản phẩm có ID " + product_id);
            session.setAttribute("cart", cart);

            // Xóa trong database
            OrderService orderService = new OrderService();
            orderService.remove(product_id);

            response.sendRedirect(request.getContextPath() + "/Cart.jsp");

        } else {
            System.out.println(" Lỗi: Không tìm thấy sản phẩm với ID " + product_id);
            response.sendRedirect("error.jsp");
        }
    }
}

