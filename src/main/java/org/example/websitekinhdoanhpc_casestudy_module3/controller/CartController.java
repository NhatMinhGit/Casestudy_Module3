package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.*;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderItemService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IProductService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.OrderItemService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.OrderService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.ProductService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "CartController", value = "/Cart")
public class CartController extends HttpServlet {
    private final IOrderItemService orderItemService = new OrderItemService();
    private final IProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            // Lấy product_id và quantity từ request
            String productIdParam = req.getParameter("product_id");
            String quantityParam = req.getParameter("quantity");
            System.out.println("Received product_id: " + productIdParam);
            System.out.println("Received quantity: " + quantityParam);

            if (productIdParam == null) {
                throw new IllegalArgumentException("Thiếu product_id");
            }

            int productId = Integer.parseInt(productIdParam);
            int quantity = (quantityParam != null) ? Integer.parseInt(quantityParam) : 1;
            if (quantityParam != null) {
                try {
                    quantity = Integer.parseInt(quantityParam);
                    if (quantity <= 0) {
                        throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Số lượng không hợp lệ");
                }
            }

            System.out.println("Product ID: " + productId + ", Quantity: " + quantity);

            Product product = productService.findById(productId);
            if (product == null) {
                System.out.println("Lỗi: Không tìm thấy sản phẩm với ID: " + productId);
            } else {
                System.out.println("Sản phẩm tìm thấy: " + product.getName());
            }


            // Xử lý giỏ hàng
            List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
                session.setAttribute("cart", cart);
            }

            boolean exists = false;
            for (OrderItem item : cart) {
                System.out.println("So sánh: item ID " + item.getProduct().getProduct_id() + " với " + productId);
                if (item.getProduct().getProduct_id() == productId) {
                    item.setQuantity(quantity);
                    System.out.println("Cập nhật số lượng sản phẩm ID " + productId + " thành: " + quantity);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                System.out.println("Sản phẩm chưa có trong giỏ hàng, thêm mới.");
            }


            if (!exists) {
                OrderItem newItem = new OrderItem(cart.size() + 1, null, product, quantity, product.getPrice());
                cart.add(newItem);
            }

            session.setAttribute("cart", cart);
            resp.getWriter().write("{\"status\": \"success\", \"message\": \"Sản phẩm đã được thêm vào giỏ hàng\"}");

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console server
            resp.getWriter().write("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
        // Kiểm tra tất cả tham số gửi đến
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            System.out.println("Received parameter: " + paramName + " = " + req.getParameter(paramName));
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Chuyển hướng đến trang giỏ hàng
        req.getRequestDispatcher("/WEB-INF/view/product/Cart.jsp").forward(req, resp);
    }
}
