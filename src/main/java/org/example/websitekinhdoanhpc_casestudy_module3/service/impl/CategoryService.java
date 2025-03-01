package org.example.websitekinhdoanhpc_casestudy_module3.service.impl;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Category;
import org.example.websitekinhdoanhpc_casestudy_module3.repository.CategoryRepository;
import org.example.websitekinhdoanhpc_casestudy_module3.service.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {
    private static CategoryRepository categoryRepository = new CategoryRepository();
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(int id) {
        categoryRepository.remove(id);
    }

    @Override
    public void update(int id, Category category) {
        categoryRepository.update(id, category);
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public List<Category> findAllByName(String name) {
        return categoryRepository.findAllByName(name);
    }
}
