package vn.codegym.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.codegym.blog.model.bean.Blog;
import vn.codegym.blog.model.bean.Category;
import vn.codegym.blog.repository.blog.BlogRepo;
import vn.codegym.blog.service.blog.BlogService;
import vn.codegym.blog.service.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("api")
public class BlogRestController {
    @Autowired
    BlogService blogService;
    @Autowired
    BlogRepo blogRepo;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/book/bookAll")
    public ResponseEntity<List<Blog>> getBlogList() {
        List<Blog> blogList = blogService.showAll();
        if (blogList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }
    @GetMapping("/book/detailBook/{id}")
    public ResponseEntity<Blog> getBookDetail(@PathVariable int id) {
       Blog blog=blogService.showDetail(id);
        if (blog==null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @GetMapping("/book/bookByCategory/{name}")
    public ResponseEntity<List<Blog>> getBookByCategory(@PathVariable String name) {
        List<Blog> blogList = blogRepo.findByCategory_Name(name);
        if (blogList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       return new ResponseEntity<>(blogList,HttpStatus.OK);
    }

    @GetMapping("/category/categoryAll")
    public ResponseEntity<List<Category>> getCategoryList() {
        List<Category> categoryList = categoryService.showAll();
        if (categoryList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }


}
