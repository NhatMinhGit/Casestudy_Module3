package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.BaseRepository;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Lấy user từ database
        User user = BaseRepository.getUserByEmail(email);

        // Kiểm tra user có tồn tại không
        if (user == null || user.getPassword() == null) {
            req.getSession().setAttribute("error", "Sai email hoặc mật khẩu!");
            resp.sendRedirect(req.getContextPath() + "/TrangChu");
            return;
        }


        if (password.equals(user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // Điều hướng theo vai trò
            if ("admin".equalsIgnoreCase(user.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else {
                resp.sendRedirect(req.getContextPath() + "/userProfile");
            }
        } else {
            req.getSession().setAttribute("error", "Sai email hoặc mật khẩu!");
            resp.sendRedirect(req.getContextPath() + "/TrangChu");
        }
    }
}
