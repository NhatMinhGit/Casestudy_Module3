package org.example.websitekinhdoanhpc_casestudy_module3.repository;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Category;
import org.example.websitekinhdoanhpc_casestudy_module3.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryRepository {

    public void save(Category category) {
    }

    public void remove(int id) {
    }

    public List<Category> findAll() {
        return null;
    }

    public Category findCategoryById(int categoryId) {
        Category category = null;
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM category WHERE category_id = ?");
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String categoryName = resultSet.getString("category_name");
                String description = resultSet.getString("description");
                category = new Category(categoryId, categoryName, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }


    public List<Category> findAllByName(String name) {
        return null;
    }


    public void update(int id, Category category) {
    }
}
