package com.example.demo.controller;

import com.example.demo.bean.Blog;
import com.example.demo.bean.Category;
import com.example.demo.service.BlogService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping(value = "/show")
    public ModelAndView showAll(@PageableDefault(value = 5) Pageable pageable) {
        Page<Category> categories = categoryService.findAllPage(pageable);
        ModelAndView modelAndView = new ModelAndView("showCategory");
        modelAndView.addObject("categoryList",categories);
        if (categories.getContent().size() == 0){
            String msgData = "No data";
            modelAndView.addObject("msgData",msgData);
        }
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView showPageCreate(){
        return new ModelAndView("createCategory","category",new Category());
    }

    @PostMapping(value = "/create")
    public String save(@ModelAttribute Category category, RedirectAttributes redirectAttributes){
        categoryService.save(category);
        return "redirect:/category/show";
    }

    @GetMapping(value = "/delete/{id}/{name}")
    public String delete(@PathVariable Long id, @PathVariable String name, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("msg", "Delete category :" + name +
                " success.");
        categoryService.remove(id);
        return "redirect:/category/show";
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView showPageEdit(@PathVariable Long id){
        return new ModelAndView("editCategory","category",categoryService.findById(id));
    }

    @PostMapping(value = "/edit")
    public String update(@ModelAttribute Category category, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("msg", "Update category :" + category.getName() +
                " success.");
        categoryService.save(category);
        return "redirect:/category/show";
    }

    @GetMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Long id){
        Category category = categoryService.findById(id);

        Iterable<Blog> blogs = blogService.findByCategory(category);

        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("blogs",blogs);
        modelAndView.addObject("category", category);
        return modelAndView;
    }

}
