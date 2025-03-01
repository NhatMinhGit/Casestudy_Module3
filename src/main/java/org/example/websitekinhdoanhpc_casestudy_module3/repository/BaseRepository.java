package org.example.websitekinhdoanhpc_casestudy_module3.repository;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;

import java.sql.*;

public class BaseRepository {
    private static final String jdbcURL = "jdbc:mysql://localhost:3307/OnlinePcShop?useSSL=false";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "123456789";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Lỗi khi tải MySQL Driver", e);
        }
    }

<<<<<<< HEAD
//    public static Connection getConnection() {
//        return connection;
//    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) { // 🔴 Đảm bảo kết nối còn mở
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinepcshop", "root", "123456789");
=======
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // ✅ Kiểm tra email đã tồn tại chưa
    public static boolean isEmailExist(String email) {
        String query = "SELECT 1 FROM Users WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
>>>>>>> DangHoangLong
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
        return connection;
=======
        return false;
    }

    // ✅ Đăng ký người dùng vào database (KHÔNG mã hóa mật khẩu)
    public static boolean registerUser(User user) {
        String query = "INSERT INTO Users (name, email, password, role, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getPhone_number());
            statement.setString(6, user.getAddress());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu đăng ký thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Lấy thông tin người dùng theo email
    public static User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT email, password, role FROM users WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password")); // Lấy mật khẩu thô
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
>>>>>>> DangHoangLong
    }
}

