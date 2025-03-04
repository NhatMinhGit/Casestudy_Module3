package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;

@WebServlet(name = "UserProfileServlet", value = "/userProfile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra xem người dùng đã đăng nhập chưa
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                // Lưu thông tin user vào request để hiển thị trong UserProfile.jsp
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/view/User/UserProfile.jsp").forward(req, resp);
            } else {
                // Nếu không có user trong session, chuyển hướng đến trang đăng nhập
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            // Nếu không có session, chuyển hướng đến trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

}