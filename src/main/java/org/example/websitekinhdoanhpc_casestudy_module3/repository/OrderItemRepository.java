package org.example.websitekinhdoanhpc_casestudy_module3.repository;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Category;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Order;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.OrderItem;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {
    OrderRepository orderRepository = new OrderRepository();
    ProductRepository productRepository = new ProductRepository();
    CategoryRepository categoryRepository = new CategoryRepository();
    public List<OrderItem> findAllOf1Order() {
        List<OrderItem> orderItemList = new ArrayList<>();//Khởi tạo danh sách chứa products
        try {
            Statement statement = BaseRepository.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orderitem");

            while (resultSet.next()) {
                int order_item_id = resultSet.getInt("order_item_id");
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


//    public OrderItem findOrderItemById(int id) {
//        return null;
//    }
    public void remove(int id) {
    }
    public void update(int id, OrderItem orderItem) {

    }
    public void save(OrderItem orderItem) {
    }
}
