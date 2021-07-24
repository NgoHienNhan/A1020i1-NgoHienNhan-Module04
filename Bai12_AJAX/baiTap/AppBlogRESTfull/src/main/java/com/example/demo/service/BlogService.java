package com.example.demo.service;

import com.example.demo.bean.bean.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();

    Blog findById(Long id);

    List<Blog> findByCategory(Long categoryId);
}
