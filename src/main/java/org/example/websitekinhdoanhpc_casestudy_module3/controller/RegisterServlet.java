package org.example.websitekinhdoanhpc_casestudy_module3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;
import org.example.websitekinhdoanhpc_casestudy_module3.Dao.UserDao;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy thông tin từ form đăng ký
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String phone_number = req.getParameter("phone_number");
        String address = req.getParameter("address");

        // Tạo đối tượng User từ thông tin form
        User newUser = new User(null, name, email, password, role, phone_number, address);

        // Kiểm tra xem email đã tồn tại trong database chưa ?
        UserDao userDao = new UserDao();
        if (userDao.isEmailExist(email)) {
            // Nếu email đã tồn tại, thông báo lỗi và trả về trang chủ
            req.setAttribute("errorMessage", "Email đã tồn tại, vui lòng chọn email khác.");
            req.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(req, resp);
        } else {
            // Nếu email chưa tồn tại, tiến hành lưu thông tin người dùng vào database
            userDao.registerUser(newUser);

            // Lưu thông tin người dùng vào session
            HttpSession session = req.getSession();
            session.setAttribute("user", newUser);

            // Chuyển hướng đến UserProfile.jsp
            resp.sendRedirect(req.getContextPath() + "/userProfile");
        }
    }
}
