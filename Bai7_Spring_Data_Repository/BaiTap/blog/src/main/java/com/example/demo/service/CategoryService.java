package com.example.demo.service;

import com.example.demo.bean.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryService {
    Page<Category> findAllPage(Pageable pageable);

    Iterable<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void remove(Long id);
}
