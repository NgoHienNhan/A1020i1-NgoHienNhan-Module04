package com.example.demo.controller;

import com.example.demo.bean.bean.Blog;
import com.example.demo.bean.bean.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/category/",method = RequestMethod.GET)
    public ResponseEntity<List<Category>> showAllCategory(){
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()){
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
    }

}
