package org.example.websitekinhdoanhpc_casestudy_module3.repository;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

        public List<Order> findAll() {
            List<Order> orders = new ArrayList<>();
            String sql = "SELECT order_id, total_price, status, order_date FROM `order`";
//        String sql = "SELECT order_id, user_id, total_price, status, order_date FROM `order`";
            try (Connection conn = BaseRepository.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Order order = new Order();
                    order.setOrder_id(rs.getInt("order_id"));
//                order.getUser().setUser_id(rs.getInt("user_id"));
                    order.setTotal_price(rs.getDouble("total_price"));
                    order.setStatus(rs.getString("status"));
                    order.setOrder_date(rs.getDate("order_date").toLocalDate());
                    orders.add(order);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return orders;
        }

        public Order findOrderById(int id) {
            String sql = "SELECT o.order_id, o.total_price, o.status, o.order_date, " +
                "u.id, u.name, u.email, u.password, u.role, u.phone, u.address " +
                "FROM `order` o " +
                "JOIN `user` u ON o.user_id = u.id " +
                "WHERE o.order_id = ?";

            try (Connection conn = BaseRepository.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Tạo đối tượng User từ kết quả truy vấn
                        User user = new User();
                        user.setUser_id(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setRole(rs.getString("role"));
                        user.setPhone_number(rs.getString("phone_number"));
                        user.setAddress(rs.getString("address"));

                        // Tạo đối tượng Order từ kết quả truy vấn
                        Order order = new Order();
                        order.setOrder_id(rs.getInt("order_id"));
                        order.setTotal_price(rs.getDouble("total_price"));
                        order.setStatus(rs.getString("status"));
                        order.setOrder_date(rs.getDate("order_date").toLocalDate());
                        order.setUser(user); // Gán đối tượng User cho Order

                        return order;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null; // Trả về null nếu không tìm thấy đơn hàng
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


//public Order createOrder(int userId) {
//    Order order = null;
//    try {
//        PreparedStatement statement = BaseRepository.getConnection()
//                .prepareStatement("INSERT INTO orders (user_id,order_date,total_price, status,shipping_address,payment_method) VALUES (?, NOW(), ?, 'PENDING', ?, ?)",
//                        Statement.RETURN_GENERATED_KEYS);
//
//        // Gán giá trị mặc định
//        double totalPrice = 0.0; // Giá trị tạm
//        String shippingAddress = "bike"; // Giá trị tạm
//        String paymentMethod = "cash"; // Giá trị tạm
//
//        statement.setInt(1, userId);
//        statement.setDouble(2, totalPrice);
//        statement.setString(3, shippingAddress);
//        statement.setString(4, paymentMethod);
//
//        // Thực thi lệnh INSERT
//        int affectedRows = statement.executeUpdate();
//
//        // Kiểm tra nếu INSERT thành công thì lấy order_id
//        if (affectedRows > 0) {
//            ResultSet resultSet = statement.getGeneratedKeys();
//            if (resultSet.next()) {
//                int orderId = resultSet.getInt(1); // Lấy cột đầu tiên (order_id)
//                LocalDate orderDate = LocalDate.now();
//
//                // Tạo User tạm thời
//                User fakeUser = new User(1, "Minh", "nhatminh091003@gmail.com", "123456789", "members", "0765599103", "20/11 Trần Quốc Tuấn");
//
//                // Tạo order mới
//                order = new Order(orderId, fakeUser, orderDate, totalPrice, "PENDING", shippingAddress, paymentMethod);
//            }
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return order;
//}


        public Order createOrder(User user) {
            if (user == null) {
                System.out.println("Không tìm thấy người dùng trong session!");
                return null;
            }

            Order order = null;
            try {
                Connection connection = BaseRepository.getConnection();
                if (connection == null) {
                    System.out.println("Lỗi: Không thể kết nối database!");
                    return null;
                }

                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO orders (user_id, order_date, total_price, status, shipping_address, payment_method) " +
                                "VALUES (?, NOW(), ?, 'PENDING', ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );

                // Giá trị mặc định
                double totalPrice = 0.0;
                String shippingAddress = "bike";
                String paymentMethod = "cash";

                System.out.println("Đang tạo đơn hàng cho User ID: " + user.getUser_id());

                statement.setInt(1, user.getUser_id());
                statement.setDouble(2, totalPrice);
                statement.setString(3, shippingAddress);
                statement.setString(4, paymentMethod);

                int affectedRows = statement.executeUpdate();
                System.out.println("Số dòng bị ảnh hưởng: " + affectedRows);

                if (affectedRows > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        int orderId = resultSet.getInt(1);
                        System.out.println("Đã tạo đơn hàng với ID: " + orderId);

                        LocalDate orderDate = LocalDate.now();
                        order = new Order(orderId, user, orderDate, totalPrice, "PENDING", shippingAddress, paymentMethod);
                    }
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return order;
        }


        public void remove(int id) {
        }
        public void updateTotalPrice(int orderId, double totalPrice) {
            String sql = "UPDATE `order` SET total_price = ? WHERE order_id = ?";

            try (Connection connection = BaseRepository.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setDouble(1, totalPrice);
                preparedStatement.setInt(2, orderId);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Lỗi khi cập nhật tổng tiền đơn hàng", e);
            }
        }
        public void save(Order order) {
            String sql = "INSERT INTO `order` (user_id, order_date, total_price, status, shipping_address, payment_method) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";



            try (Connection connection = BaseRepository.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setInt(1, 1); // Lưu user_id
                preparedStatement.setDate(2, Date.valueOf(order.getOrder_date())); // Lưu order_date
                preparedStatement.setDouble(3, order.getTotal_price()); // Lưu tổng tiền
                preparedStatement.setString(4, order.getStatus()); // Lưu trạng thái đơn hàng
                preparedStatement.setString(5, order.getShipping_address()); // Lưu địa chỉ giao hàng
                preparedStatement.setString(6, order.getPayment_method()); // Lưu phương thức thanh toán

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            order.setOrder_id(generatedKeys.getInt(1)); // Gán ID đơn hàng mới tạo
                        }
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException("Lỗi khi lưu đơn hàng vào cơ sở dữ liệu", e);
            }
        }
        public boolean updateOrderStatus(int orderId, String status) {
            String sql = "UPDATE `order` SET status = ? WHERE order_id = ?";

            try (Connection conn = BaseRepository.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, status);
                stmt.setInt(2, orderId);

                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0; // Trả về true nếu cập nhật thành công
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false; // Cập nhật thất bại
        }

        public Order findLastInsertedOrder() {
        String query = "SELECT * FROM `order` ORDER BY order_id DESC LIMIT 1";
        Order order = null;

        try (Connection conn = BaseRepository.getConnection()) {
            if (conn.isClosed()) { // Kiểm tra nếu kết nối đã đóng
                System.out.println("Kết nối đã bị đóng!");
                return null;
            }

            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    order = new Order();
                    order.setOrder_id(rs.getInt("order_id"));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }




    public void update(int id, Order order) {
    }

    public List<Order> findOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ?";

        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrder_id(rs.getInt("order_id"));
                    order.setOrder_date(rs.getDate("order_date").toLocalDate());
                    order.setTotal_price(rs.getDouble("total_price"));
                    order.setStatus(rs.getString("status"));
                    orders.add(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
