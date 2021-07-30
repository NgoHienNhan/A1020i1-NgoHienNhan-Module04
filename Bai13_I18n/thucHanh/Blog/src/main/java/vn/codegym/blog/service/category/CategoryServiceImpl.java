package vn.codegym.blog.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.blog.model.bean.Category;
import vn.codegym.blog.repository.category.CategoryRepo;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Category> showAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category showDetail(int id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public void addCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepo.delete(category);
    }

    @Override
    public Page<Category> showAllPage(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

    @Override
    public Page<Category> findCategory(Pageable pageable, String name) {
        return null;
    }
}
