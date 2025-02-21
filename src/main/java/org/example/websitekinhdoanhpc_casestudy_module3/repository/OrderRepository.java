package org.example.websitekinhdoanhpc_casestudy_module3.repository;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Category;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class OrderRepository {


    public List<Order> findAll() {
        return null;
    }

    public Order findOrderById(int id) {
        return null;
    }
//    public Order createOrder(int userId) {
//        Order order = null;
//        try {
//            PreparedStatement statement = BaseRepository.getConnection()
//                    .prepareStatement("INSERT INTO orders (user_id,order_date,total_price, status,shipping_address,payment_method) VALUES (?, NOW(),?, 'PENDING',?,?)");
////            statement.setInt(1, userId);
//            statement.executeUpdate();
//
//            ResultSet resultSet = statement.getGeneratedKeys();
//            if (resultSet.next()) {
//                int orderId = resultSet.getInt("order_id");
//                LocalDate order_date = LocalDate.now();
//                double total_price = 0.0;
//
//                order = new Order(orderId, new User(1,"Minh","nhatminh091003@gmail.com","123456789","members","0765599103","20/11 Trần Quốc Tuấn"), order_date, total_price,"PENDING","bike","cash");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return order;
//    }
public Order createOrder(int userId) {
    Order order = null;
    try {
        PreparedStatement statement = BaseRepository.getConnection()
                .prepareStatement("INSERT INTO orders (user_id,order_date,total_price, status,shipping_address,payment_method) VALUES (?, NOW(), ?, 'PENDING', ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

        // Gán giá trị mặc định
        double totalPrice = 0.0; // Giá trị tạm
        String shippingAddress = "bike"; // Giá trị tạm
        String paymentMethod = "cash"; // Giá trị tạm

        statement.setInt(1, userId);
        statement.setDouble(2, totalPrice);
        statement.setString(3, shippingAddress);
        statement.setString(4, paymentMethod);

        // Thực thi lệnh INSERT
        int affectedRows = statement.executeUpdate();

        // Kiểm tra nếu INSERT thành công thì lấy order_id
        if (affectedRows > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1); // Lấy cột đầu tiên (order_id)
                LocalDate orderDate = LocalDate.now();

                // Tạo User tạm thời
                User fakeUser = new User(1, "Minh", "nhatminh091003@gmail.com", "123456789", "members", "0765599103", "20/11 Trần Quốc Tuấn");

                // Tạo order mới
                order = new Order(orderId, fakeUser, orderDate, totalPrice, "PENDING", shippingAddress, paymentMethod);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return order;
}

    public void remove(int id) {
    }

    public void save(Order order) {
    }
}
