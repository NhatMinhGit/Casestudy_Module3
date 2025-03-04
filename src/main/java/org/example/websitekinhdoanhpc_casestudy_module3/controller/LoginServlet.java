package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.BaseRepository;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.UserRepository;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Lấy user từ database
        User user = UserRepository.getUserByEmail(email);

        if (user == null || !password.equals(user.getPassword())) {
            req.setAttribute("error", "Sai email hoặc mật khẩu!");
            req.getRequestDispatcher("/userProfile").forward(req, resp);
            return;
        }

        // Lưu user vào session
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        // Kiểm tra xem có trang nào cần quay lại không (VD: checkout)
        String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
        if (redirectUrl != null) {
            session.removeAttribute("redirectAfterLogin");
            resp.sendRedirect(redirectUrl);
        } else if ("admin".equalsIgnoreCase(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/admin");
        } else {
            resp.sendRedirect(req.getContextPath() + "/userProfile"); // Chuyển về trang chủ
        }
    }
}

