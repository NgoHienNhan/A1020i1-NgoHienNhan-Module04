package com.example.demo.repository;

import com.example.demo.bean.bean.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findByCategory_Id(Long id);
}
