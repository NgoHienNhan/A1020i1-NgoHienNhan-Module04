package vn.codegym.service;

import vn.codegym.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    void save(Category category);
    boolean delete(Integer id);
    boolean existsByURLName(String URLName);
    List<Category> findByURLName(String URLName);
}
