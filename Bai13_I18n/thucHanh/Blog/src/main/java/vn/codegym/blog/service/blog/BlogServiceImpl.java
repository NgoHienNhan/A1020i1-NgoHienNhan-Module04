package vn.codegym.blog.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.blog.model.bean.Blog;
import vn.codegym.blog.repository.blog.BlogRepo;

import java.util.List;
@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    BlogRepo blogRepo;
    @Override
    public List<Blog> showAll() {
        return blogRepo.findAll();
    }


    @Override
    public Blog showDetail(int id) {
        return blogRepo.findById(id).orElse(null);
    }

    @Override
    public void addBlog(Blog blog) {
        blogRepo.save(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogRepo.save(blog);
    }

    @Override
    public void deleteBlog(Blog blog) {
        blogRepo.delete(blog);
    }

    @Override
    public Page<Blog> showAllPage(Pageable pageable) {
        return blogRepo.findAll(pageable);
    }
    @Override
    public Page<Blog> findBlog(Pageable pageable,String name) {
        return blogRepo.findBlog(pageable,name);
    }
}
