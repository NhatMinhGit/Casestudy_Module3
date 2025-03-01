package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.OrderService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckoutController", value = "/Cart/ThanhToan")
public class CheckoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderService orderService = new OrderService(); // Thêm OrderService

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            // Lấy order_id từ request hoặc session
            String orderIdParam = request.getParameter("order_id");
            int orderId = 0;

            if (orderIdParam != null && !orderIdParam.isEmpty()) {
                try {
                    orderId = Integer.parseInt(orderIdParam);
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: order_id không hợp lệ từ request!");
                }
            } else {
                Integer sessionOrderId = (Integer) request.getSession().getAttribute("order_id");
                if (sessionOrderId != null) {
                    orderId = sessionOrderId;
                }
            }

            // Kiểm tra nếu orderId không hợp lệ
            if (orderId == 0) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Lỗi: Không tìm thấy order_id hợp lệ!");
                return;
            }

            // Cập nhật trạng thái đơn hàng trong database
            boolean isUpdated = orderService.updateOrderStatus(orderId, "PAID");

            if (isUpdated) {
                response.getWriter().write("Thanh toán thành công! Đơn hàng đã được cập nhật.");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Lỗi: Không thể cập nhật trạng thái đơn hàng.");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Lỗi: " + e.getMessage());
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/view/product/HoaDonCuaToi.jsp").forward(request, response);
    }
}
