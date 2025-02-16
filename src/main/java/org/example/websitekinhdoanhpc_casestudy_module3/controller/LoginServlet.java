package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.websitekinhdoanhpc_casestudy_module3.utils.DatabaseUtil;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT password FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (password.equals(storedPassword)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", email);
                    resp.sendRedirect("TrangChu.jsp");
                } else {
                    req.setAttribute("error", "Sai mật khẩu!");
                    req.getRequestDispatcher("/WEB-INF/view/product/TrangChu.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "Email không tồn tại!");
                req.getRequestDispatcher("/WEB-INF/view/product/TrangChu.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("error", "Lỗi Database: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/product/TrangChu.jsp").forward(req, resp);
        }
    }
}
