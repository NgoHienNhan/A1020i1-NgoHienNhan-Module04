package com.example.demo.service;

import com.example.demo.bean.Blog;
import com.example.demo.bean.Category;
import com.example.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return (List<Blog>) blogRepository.findAll();
    }

    @Override
    public Page<Blog> findAllByOrderByDateDesc(Pageable pageable) {
        return blogRepository.findAllByOrOrderByDateDesc(pageable);
    }

    @Override
    public Page<Blog> findByTitle(String searchValue, Pageable pageable) {
        return blogRepository.findByTitleLike(searchValue,pageable);
    }

    @Override
    public Iterable<Blog> findByCategory(Category category) {
        return blogRepository.findByCategory(category);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }
}
