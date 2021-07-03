package com.example.model.repository;

import com.example.model.bean.Product;

import java.util.List;

public interface ProductRepo {
    List<Product> findAll();

    void save(Product product);

    Product findById(int id);

    void update(int id, Product product);

    void remove(int id);
}
