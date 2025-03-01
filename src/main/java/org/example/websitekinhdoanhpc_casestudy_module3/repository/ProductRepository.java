package org.example.websitekinhdoanhpc_casestudy_module3.repository;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Category;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static List<Product> products = new ArrayList<>();
    CategoryRepository categoryRepository = new CategoryRepository();

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();//Khởi tạo danh sách chứa products
        try {
            Statement statement = BaseRepository.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

            while (resultSet.next()) {
                int product_id = resultSet.getInt("product_id");
                int category_id = resultSet.getInt("category_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image_url = resultSet.getString("image_url");
                String description = resultSet.getString("description");
                int stock_quantity = resultSet.getInt("stock_quantity");

                // Gọi phương thức lấy đầy đủ thông tin của Category
                Category category = categoryRepository.findCategoryById(category_id);

                products.add(new Product(product_id, category, name, price,image_url, description, stock_quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }


    public List<Product> findAllByName(String name) {
        List<Product> temp = new ArrayList<>();
        for (Product product: products) {
            if (product.getName().contains(name)) {
                temp.add(product);
            }
        }
        return temp;
    }

    public void save(Product product) {
        try {
            PreparedStatement statement = BaseRepository.getConnection()
                    .prepareStatement("insert into product(category_id, name,price, description, stock_quantity) values (?,?,?,?,?)");
            statement.setInt(1, product.getCategory().getCategory_id());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getStock_quantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product findById(int id) {
        try{
            PreparedStatement statement = BaseRepository.getConnection()
                    .prepareStatement("select * from product where product_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int product_id = resultSet.getInt("product_id");
                int category_id = resultSet.getInt("category_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image_url = resultSet.getString("image_url");
                String description = resultSet.getString("description");
                int stock_quantity = resultSet.getInt("stock_quantity");
                // Gọi phương thức lấy đầy đủ thông tin của Category
                Category category = categoryRepository.findCategoryById(category_id);
                return new Product(product_id, category, name, price,image_url, description, stock_quantity);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public void remove(int id) {
        try {
            PreparedStatement statement = BaseRepository.getConnection()
                    .prepareStatement("delete from product where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Product product) {
    }
}
