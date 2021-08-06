package vn.codegym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.model.Category;
import vn.codegym.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public boolean delete(Integer id) {
        categoryRepository.deleteById(id);
        return false;
    }

    @Override
    public boolean existsByURLName(String URLName) {
        return categoryRepository.existsByURLName(URLName);
    }

    @Override
    public List<Category> findByURLName(String URLName) {
        return categoryRepository.findByURLName(URLName);
    }
}
