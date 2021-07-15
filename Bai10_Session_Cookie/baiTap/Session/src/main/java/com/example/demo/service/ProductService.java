package com.example.demo.service;

import com.example.demo.bean.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    void save(Product product);

    void delete(Product product);
}
