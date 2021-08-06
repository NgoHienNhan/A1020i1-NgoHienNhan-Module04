package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.model.Blog;
import vn.codegym.model.BlogShow;
import vn.codegym.model.Category;
import vn.codegym.service.BlogService;
import vn.codegym.service.CategoryService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogRestController {
    @Autowired
    private BlogService service;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/category")
    public ResponseEntity<List<String>> listCategory(){
        List<Category> categoryList = categoryService.findAll();
        List<String> list = new ArrayList<>();
        for (Category category : categoryList){
            list.add(category.getName());
        }
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/blog")
    public ResponseEntity<Page<Blog>> listBlog(@PageableDefault(2) Pageable pageable){
        Page<Blog> page = service.findAll(pageable);
        if (page.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        for (Blog blog : page){
            blog.setDateDisplay(formatter.format(blog.getDate()));
            blog.getCategory().setBlogs(null);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = "/blog/{category}")
    public ResponseEntity<List<Blog>> listBlogCategory(@PathVariable String category){
        List<Blog> blogList = service.findAllByCategory_URLName(category);
        if (blogList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Blog blog : blogList){
            blog.setCategory(null);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @GetMapping(value = "/single-blog/{URLTitle}")
    public ResponseEntity<Blog> signBlog(@PathVariable String URLTitle){
        Blog blog = service.findByURLTitle(URLTitle);
        if (blog == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        blog.setCategory(null);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @GetMapping(value = "/api/search")
    public ResponseEntity<Page<Blog>> searchBlog(@RequestParam String value, @PageableDefault(1) Pageable pageable){
        Page<Blog> page = service.findAllByTitleContaining(value, pageable);
        if (page.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        for (Blog blog : page){
            blog.setDateDisplay(formatter.format(blog.getDate()));
            blog.getCategory().setBlogs(null);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
