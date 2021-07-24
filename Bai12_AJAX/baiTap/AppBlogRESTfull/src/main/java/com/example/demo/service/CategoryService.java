package com.example.demo.service;

import com.example.demo.bean.bean.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findByID(Long id);
}
