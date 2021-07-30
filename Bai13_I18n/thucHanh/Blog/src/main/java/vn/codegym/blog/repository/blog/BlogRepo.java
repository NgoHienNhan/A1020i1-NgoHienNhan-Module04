package vn.codegym.blog.repository.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.codegym.blog.model.bean.Blog;

import java.util.List;

public interface BlogRepo extends JpaRepository<Blog,Integer> {
    @Query("select c from Blog c where c.title like %:name%")
    Page<Blog> findBlog(Pageable page,@Param("name") String name);

    List<Blog> findByCategory_Name(String name);
}
