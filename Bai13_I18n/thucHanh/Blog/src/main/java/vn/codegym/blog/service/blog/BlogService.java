package vn.codegym.blog.service.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.blog.model.bean.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> showAll();
    Blog showDetail(int id);
    void addBlog(Blog blog);
    void updateBlog(Blog blog);
    void deleteBlog(Blog blog);
    Page<Blog> showAllPage(Pageable pageable);
    Page<Blog> findBlog(Pageable pageable,String name);
}
