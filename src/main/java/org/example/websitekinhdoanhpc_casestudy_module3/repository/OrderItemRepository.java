package org.example.websitekinhdoanhpc_casestudy_module3.repository;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Category;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Product;

import java.sql.*;
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
    public void save(OrderItem orderItem) {
        String sql = "INSERT INTO orderitem (order_id, product_id, quantity, price_per_unit) VALUES (?, ?, ?, ?)";
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, orderItem.getOrder().getOrder_id()); // Lấy ID của Order
            preparedStatement.setInt(2, orderItem.getProduct().getProduct_id()); // Lấy ID của Product
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setDouble(4, orderItem.getPrice_per_unit());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm OrderItem vào cơ sở dữ liệu", e);
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
}
