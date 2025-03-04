package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;

import org.example.websitekinhdoanhpc_casestudy_module3.repository.UserRepository;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phone_number"); // Lấy số điện thoại từ form
        String address = req.getParameter("address"); // Lấy địa chỉ từ form
        String role = "customer"; // Mặc định user đăng ký là khách hàng

        // Kiểm tra xem email đã tồn tại chưa
        User existingUser = UserRepository.getUserByEmail(email);
        System.out.println("Debug - Checking email: " + email);
        System.out.println("Debug - Existing user: " + (existingUser != null ? existingUser.getEmail() : "null"));

        if (existingUser != null) {
            req.setAttribute("error", "Email đã được sử dụng!");
            req.setAttribute("showRegisterModal", "true");
            req.getRequestDispatcher("/WEB-INF/view/product/TrangChu.jsp").forward(req, resp);
            return;
        }

        // Tạo user mới với đầy đủ thông tin
        User newUser = new User(null, name, email, password, role, phoneNumber, address);
        boolean success = UserRepository.registerUser(newUser);

        if (success) {
            HttpSession session = req.getSession();
            session.setAttribute("user", newUser); // Lưu user vào session để hiển thị thông tin trên trang hồ sơ
            session.setAttribute("message", "Đăng ký thành công!");
            resp.sendRedirect(req.getContextPath() + "/userProfile");
        } else {
            req.setAttribute("error", "Đăng ký thất bại, vui lòng thử lại!");
            req.setAttribute("showRegisterModal", "true");
            req.getRequestDispatcher("/WEB-INF/view/product/TrangChu.jsp").forward(req, resp);
        }
    }
}
