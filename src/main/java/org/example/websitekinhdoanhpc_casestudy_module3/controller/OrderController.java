package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.RequestDispatcher;
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
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IOrderService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.IProductService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.OrderService;
import org.example.websitekinhdoanhpc_casestudy_module3.service.impl.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/orders")
public class OrderController extends HttpServlet {

    private IOrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Lấy session
        HttpSession session = request.getSession(false); // Không tạo session mới nếu chưa có
        if (session == null || session.getAttribute("userId") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Bạn chưa đăng nhập!\"}");
            return;
        }

        // Lấy userId từ session
        int userId = (int) session.getAttribute("userId");

        // Gọi service để lấy danh sách đơn hàng
        List<Order> orders = orderService.findOrdersByUserId(userId);
        request.setAttribute("order", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/product/HoaDonCuaToi.jsp");
        dispatcher.forward(request, response);

        // Chuyển danh sách đơn hàng thành JSON
        String json = new Gson().toJson(orders);

        // Trả JSON về client
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

}
