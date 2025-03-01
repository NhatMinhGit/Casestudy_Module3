package org.example.websitekinhdoanhpc_casestudy_module3.repository;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {
    OrderRepository orderRepository = new OrderRepository();
    ProductRepository productRepository = new ProductRepository();
    CategoryRepository categoryRepository = new CategoryRepository();
    public List<OrderItem> findAllOf1Order(int order_item_id) {
        List<OrderItem> orderItemList = new ArrayList<>();//Khởi tạo danh sách chứa products
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orderitem WHERE order_id = ?");
            preparedStatement.setInt(1, order_item_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int quantity = resultSet.getInt("quantity");
                double price_per_unit = resultSet.getDouble("price_per_unit");

//                int category_id = resultSet.getInt("category_id");
//                // Gọi phương thức lấy đầy đủ thông tin của Category
//                Category category = categoryRepository.findCategoryById(category_id);

                // Gọi phương thức lấy đầy đủ thông tin của Order
                int order_id = resultSet.getInt("order_id");
                Order order = orderRepository.findOrderById(order_id);

                // Gọi phương thức lấy đầy đủ thông tin của Product
                int product_id = resultSet.getInt("product_id");
                Product product = productRepository.findById(product_id);

                orderItemList.add(new OrderItem(order_item_id, order, product, quantity, price_per_unit));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItemList;

    }
    public List<OrderItem> findOrderItemsByOrderId(int order_id) {
        List<OrderItem> orderItemList = new ArrayList<>();

        String query = "SELECT oi.order_item_id, oi.quantity, oi.price_per_unit, "
                + "o.order_id, o.user_id, o.order_date, o.total_price, o.status, o.shipping_address, o.payment_method, "
                + "p.product_id, p.name AS product_name, p.price, p.image_url, p.description, p.stock_quantity, p.category_id, "
                + "c.category_id, c.category_name AS category_name, c.description AS category_description, "
                + "u.user_id, u.name AS user_name, u.email, u.phone_number, u.address, u.password, u.role "
                + "FROM `orderitem` oi "
                + "JOIN `order` o ON oi.order_id = o.order_id "
                + "JOIN `product` p ON oi.product_id = p.product_id "
                + "JOIN `category` c ON p.category_id = c.category_id "
                + "JOIN `user` u ON o.user_id = u.user_id "
                + "WHERE oi.order_id = ?";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, order_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int orderItemId = resultSet.getInt("order_item_id");
                int quantity = resultSet.getInt("quantity");
                double pricePerUnit = resultSet.getDouble("price_per_unit");

                // Lấy thông tin User
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                User user = new User(userId, userName, email, phoneNumber, address, password, role);

                // Lấy thông tin Order
                int fetchedOrderId = resultSet.getInt("order_id");
                LocalDate orderDate = resultSet.getDate("order_date") != null
                        ? resultSet.getDate("order_date").toLocalDate()
                        : null;
                double totalPrice = resultSet.getDouble("total_price");
                String status = resultSet.getString("status");
                String shippingAddress = resultSet.getString("shipping_address");
                String paymentMethod = resultSet.getString("payment_method");
                Order order = new Order(fetchedOrderId, user, orderDate, totalPrice, status, shippingAddress, paymentMethod);

                // Lấy thông tin Category
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");
                String categoryDescription = resultSet.getString("category_description");
                Category category = new Category(categoryId, categoryName, categoryDescription);

                // Lấy thông tin Product
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("price");
                String imageUrl = resultSet.getString("image_url");
                String productDescription = resultSet.getString("description");
                int stockQuantity = resultSet.getInt("stock_quantity");
                Product product = new Product(productId, category, productName, productPrice, imageUrl, productDescription, stockQuantity);

                // Tạo OrderItem và thêm vào danh sách
                orderItemList.add(new OrderItem(orderItemId, order, product, quantity, pricePerUnit));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItemList;
    }




    public void remove(int id) {
        String sql = "DELETE FROM orderitem WHERE product_id = ?";
        try (Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateItemInCart(int order_item_id, OrderItem orderItem) {
        String sql = "UPDATE orderitem SET quantity = ? WHERE order_item_id = ? AND product_id = ?";
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderItem.getQuantity());
            preparedStatement.setInt(2, order_item_id);
            preparedStatement.setInt(3, orderItem.getProduct().getProduct_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật số lượng OrderItem vào giỏ hàng", e);
        }
    }
    public double calculateTotalPrice(int orderId) {
        double total = 0.0;

        String sql = "SELECT SUM(quantity * price_per_unit) AS total_price FROM orderitem WHERE order_id = ?";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    total = resultSet.getDouble("total_price");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tính tổng tiền đơn hàng", e);
        }

        return total;
    }
    public void save(OrderItem orderItem) {
        String sql = "INSERT INTO orderitem (order_id, product_id, quantity, price_per_unit) VALUES (?, ?, ?, ?)";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Kiểm tra Order và Product không null
            if (orderItem.getOrder() == null || orderItem.getProduct() == null) {
                throw new IllegalArgumentException("Order hoặc Product không được null");
            }

            preparedStatement.setInt(1, orderItem.getOrder().getOrder_id()); // Lấy ID của Order
            preparedStatement.setInt(2, orderItem.getProduct().getProduct_id()); // Lấy ID của Product
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setDouble(4, orderItem.getPrice_per_unit());

            int affectedRows = preparedStatement.executeUpdate();

            // Lấy ID tự động tạo (nếu có)
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderItem.setOrder_item_id(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm OrderItem vào cơ sở dữ liệu: " + e.getMessage(), e);
        }
    }


    public boolean addProductToOrder(int orderId, int productId) {
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, 1)"
            );
            statement.setInt(1, orderId);
            statement.setInt(2, productId);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void update(OrderItem item) {
    }
}
