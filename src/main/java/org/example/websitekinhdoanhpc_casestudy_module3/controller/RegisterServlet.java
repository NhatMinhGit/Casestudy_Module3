package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.BaseRepository;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = "customer"; // Mặc định user đăng ký là khách hàng

        // Kiểm tra xem email đã tồn tại chưa
        if (BaseRepository.getUserByEmail(email) != null) {
            req.getSession().setAttribute("error", "Email đã được sử dụng!");
            resp.sendRedirect(req.getContextPath() + "/register.jsp");
            return;
        }

        // Tạo user mới (KHÔNG mã hóa mật khẩu)
        User newUser = new User(null, name, email, password, role, null, null);
        boolean success = BaseRepository.registerUser(newUser);

        if (success) {
            // Đăng ký thành công => Lưu user vào session và chuyển hướng đến /userProfile
            HttpSession session = req.getSession();
            session.setAttribute("user", newUser);
            session.setAttribute("message", "Đăng ký thành công!");
            resp.sendRedirect(req.getContextPath() + "/userProfile");
        } else {
            req.getSession().setAttribute("error", "Đăng ký thất bại, vui lòng thử lại!");
            resp.sendRedirect(req.getContextPath() + "/register.jsp");
        }
    }
}
