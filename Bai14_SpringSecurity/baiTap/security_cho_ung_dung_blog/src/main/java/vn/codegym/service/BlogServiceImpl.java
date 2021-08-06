package vn.codegym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.model.Blog;
import vn.codegym.repository.BlogRepository;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(Integer id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Blog blog) {
        Date now = new Date();
        blog.setDate(now);
        blogRepository.save(blog);
    }

    @Override
    public boolean delete(Integer id) {
        blogRepository.deleteById(id);
        return false;
    }

    @Override
    public boolean existsByURLTitle(String URLTitle) {
        return blogRepository.existsByURLTitle(URLTitle);
    }

    @Override
    public Blog findByURLTitle(String URLTitle) {
        return blogRepository.findByURLTitle(URLTitle);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findAllByTitleContaining(String value, Pageable pageable) {
        return blogRepository.findAllByTitleContaining(value, pageable);
    }

    @Override
    public Page<Blog> findAllByCategory_Name(String category, Pageable pageable) {
        return blogRepository.findAllByCategory_Name(category, pageable);
    }

    @Override
    public Page<Blog> findAllByCategory_URLName(String URLCategory, Pageable pageable) {
        return blogRepository.findAllByCategory_URLName(URLCategory, pageable);
    }

    @Override
    public List<Blog> findAllByCategory_URLName(String nameCategory) {
        return blogRepository.findAllByCategory_URLName(nameCategory);
    }

    @Override
    public List<Blog> findAllByTitleContaining(String value) {
        return blogRepository.findAllByTitleContaining(value);
    }
}
