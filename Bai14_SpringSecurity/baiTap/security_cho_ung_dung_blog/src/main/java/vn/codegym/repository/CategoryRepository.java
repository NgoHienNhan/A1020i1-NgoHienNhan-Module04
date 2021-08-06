package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByURLName(String URLName);
    List<Category> findByURLName(String URLName);
}
