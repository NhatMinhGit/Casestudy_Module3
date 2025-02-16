package org.example.websitekinhdoanhpc_casestudy_module3.service;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();

    void save(Category t);

    void remove(int id);

    void update(int id, Category t);

    Category findCategoryById(int id);

    List<Category> findAllByName(String name);
}
