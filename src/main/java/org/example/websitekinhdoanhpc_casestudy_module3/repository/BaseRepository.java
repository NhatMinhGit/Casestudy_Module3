package org.example.websitekinhdoanhpc_casestudy_module3.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    private static final String jdbcURL = "jdbc:mysql://localhost:3307/OnlinePcShop?useSSL=false";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "123456789";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

//    public static Connection getConnection() {
//        return connection;
//    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) { // 🔴 Đảm bảo kết nối còn mở
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinepcshop", "root", "123456789");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
