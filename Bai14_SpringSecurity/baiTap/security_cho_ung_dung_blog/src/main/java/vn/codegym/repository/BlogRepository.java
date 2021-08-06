package vn.codegym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.model.Blog;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    boolean existsByURLTitle(String URLTitle);
    Page<Blog> findAllByTitleContaining(String value, Pageable pageable);
    List<Blog> findAllByTitleContaining(String value);
    Page<Blog> findAllByCategory_Name(String category, Pageable pageable);
    Blog findByURLTitle(String URLTitle);
    Page<Blog> findAllByCategory_URLName(String URLCategory, Pageable pageable);
    List<Blog> findAllByCategory_URLName(String nameCategory);
}
