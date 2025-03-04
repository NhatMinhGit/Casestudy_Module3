package org.example.websitekinhdoanhpc_casestudy_module3.repository;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.websitekinhdoanhpc_casestudy_module3.repository.BaseRepository.getConnection;

public class UserRepository {// ✅ Kiểm tra email đã tồn tại chưa
    public static boolean isEmailExist(String email) {
        String query = "SELECT 1 FROM Users WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Đăng ký người dùng vào database
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
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Lấy thông tin người dùng theo email
    public static User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT id, name, email, password, role FROM Users WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            System.out.println("Debug - Querying email: " + email); // Debug
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { // Nếu có kết quả, user được tạo
                System.out.println("Debug - Email found in database: " + rs.getString("email")); // Debug
                user = new User();
                user.setUser_id(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            } else {
                System.out.println("Debug - No user found for email: " + email); // Debug
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
