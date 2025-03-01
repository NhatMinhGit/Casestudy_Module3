package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.BaseRepository;
import org.example.websitekinhdoanhpc_casestudy_module3.utils.PasswordUtil;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection conn = BaseRepository.getConnection()) {
            String sql = "SELECT user_id, name, email, password, role, phone_number, address FROM User WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (PasswordUtil.checkPassword(password, storedPassword)) {
                    // Tạo đối tượng User với thông tin từ DB
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("phone_number"),
                            rs.getString("address")
                    );

                    // Lưu thông tin User vào session
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);

                    // Chuyển hướng đến UserProfileServlet để hiển thị thông tin người dùng
                    resp.sendRedirect(req.getContextPath() + "/userProfile");
                } else {
                    req.setAttribute("error", "Sai mật khẩu!");
                    req.getRequestDispatcher("/WEB-INF/view/user/UserProfile.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "Email không tồn tại!");
                req.getRequestDispatcher("/WEB-INF/view/user/UserProfile.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("error", "Lỗi Database: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/User/UserProfile.jsp").forward(req, resp);
        }
    }
}
