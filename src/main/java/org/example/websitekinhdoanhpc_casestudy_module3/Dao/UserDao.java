package org.example.websitekinhdoanhpc_casestudy_module3.Dao;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.BaseRepository;

import java.sql.*;

public class UserDao {

    public boolean isEmailExist(String email) {
        boolean exists = false;
        String query = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            exists = resultSet.next(); // Nếu có kết quả, email đã tồn tại
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    // Hàm đăng ký người dùng
    public void registerUser(User user) {
        String query = "INSERT INTO users (name, email, password, role, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getPhone_number());
            statement.setString(6, user.getAddress());
            statement.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
