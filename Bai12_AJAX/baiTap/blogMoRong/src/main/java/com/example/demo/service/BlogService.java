package com.example.demo.service;


import com.example.demo.bean.Blog;
import com.example.demo.bean.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    List<Blog> findAll();

    Page<Blog> findAllByOrderByDateDesc(Pageable pageable);

    Page<Blog> findByTitle(String searchValue, Pageable pageable);

    Iterable<Blog> findByCategory(Category category);

    Blog findById(Long id);

    void save(Blog blog);

    void remove(Long id);
}
