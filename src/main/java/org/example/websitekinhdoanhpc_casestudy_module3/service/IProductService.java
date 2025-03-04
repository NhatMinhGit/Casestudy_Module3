package org.example.websitekinhdoanhpc_casestudy_module3.service;

import org.example.websitekinhdoanhpc_casestudy_module3.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();

    void save(Product t);

    void remove(int id);

    void update(int id, Product t);

    Product findById(int id);

    List<Product> findAllByName(String name);
}