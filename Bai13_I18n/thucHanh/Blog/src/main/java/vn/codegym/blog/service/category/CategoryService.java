package vn.codegym.blog.service.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.blog.model.bean.Blog;
import vn.codegym.blog.model.bean.Category;

import java.util.List;

public interface CategoryService {
    List<Category> showAll();
    Category showDetail(int id);
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Category category);
    Page<Category> showAllPage(Pageable pageable);
    Page<Category> findCategory(Pageable pageable,String name);
}
