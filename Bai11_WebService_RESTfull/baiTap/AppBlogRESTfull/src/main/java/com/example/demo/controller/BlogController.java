package com.example.demo.controller;

import com.example.demo.bean.bean.Blog;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/blogs/",method = RequestMethod.GET)
    public ResponseEntity<List<Blog>> showAllBlog(){
        List<Blog> blogs = blogService.findAll();
        if (blogs.isEmpty()){
            return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
    }

    @RequestMapping(value = "/blogs/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> showBlogById(@PathVariable("id") long id){
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }

    @RequestMapping(value = "/blogs/category/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blog>> showBlogByIdCategory(@PathVariable("id") long id){
        List<Blog> blogs = blogService.findByCategory(id);
        if (blogs == null) {
            return new ResponseEntity<List<Blog>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
    }
}
