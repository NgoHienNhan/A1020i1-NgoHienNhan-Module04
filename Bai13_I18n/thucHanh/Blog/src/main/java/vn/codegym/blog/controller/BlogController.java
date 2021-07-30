package vn.codegym.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.blog.model.bean.Blog;
import vn.codegym.blog.service.blog.BlogService;
import vn.codegym.blog.service.category.CategoryService;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/")
    public String homePage(Model model, @PageableDefault(value = 2) Pageable pageable) {
        model.addAttribute("listBlog", blogService.showAllPage(pageable));
        return "blog/index";
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam String name,@PageableDefault(value = 2)Pageable pageable){
        return new ModelAndView("blog/index","listBlog",blogService.findBlog(pageable,name));
    }

    @GetMapping("/showCreate")
    public ModelAndView showCreate() {
        ModelAndView modelAndView=new ModelAndView("blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("categoryList",categoryService.showAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes) {
        blogService.addBlog(blog);
        redirectAttributes.addFlashAttribute("msg", "Add success blog: " + blog.getTitle());
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id) {
        ModelAndView modelAndView =new ModelAndView("blog/edit");
        modelAndView.addObject("Blog", blogService.showDetail(id));
        modelAndView.addObject("categoryList",categoryService.showAll());
        return modelAndView;
    }
    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable int id) {
        return new ModelAndView("blog/detail", "Blog", blogService.showDetail(id));
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Blog Blog,RedirectAttributes redirectAttributes) {
        blogService.updateBlog(Blog);
        redirectAttributes.addFlashAttribute("msg","Update "+Blog.getTitle()+" success");
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes){
        Blog blog=blogService.showDetail(id);
        blogService.deleteBlog(blog);
        redirectAttributes.addFlashAttribute("msg","Delete "+blog.getTitle()+" success");
        return "redirect:/";
    }
}
