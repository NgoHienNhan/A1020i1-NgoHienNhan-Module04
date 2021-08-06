package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.model.Category;
import vn.codegym.model.User;
import vn.codegym.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("admin")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @ModelAttribute("admin")
    public User admin(){
        return new User();
    }

    @ModelAttribute("listCategory")
    public List<Category> listCategory(){
        return categoryService.findAll();
    }

    @GetMapping(value = "/list-category")
    public ModelAndView viewAll(@ModelAttribute("admin") User user){
        if ((user.getUserName() == null) || (user.getPassword() == null)){
            return new ModelAndView("error/error-404");
        }
        return new ModelAndView("category/listCategory");
    }

    @GetMapping(value = "/create-category")
    public String viewCreate(@ModelAttribute("admin") User user, Model model){
        if ((user.getUserName() == null) || (user.getPassword() == null)){
            return "error/error-404";
        }
        model.addAttribute("category", new Category());
        return "category/createCategory";
    }

    @PostMapping(value = "/create-category")
    public String saveCreate(@Valid @ModelAttribute Category category, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Model model, @ModelAttribute("admin") User user){
        if ((user.getUserName() == null) || (user.getPassword() == null)){
            return "error/error-404";
        }
        if (bindingResult.hasErrors()){
            return "category/createCategory";
        }else {
            if (categoryService.existsByURLName(category.getURLName())){
                model.addAttribute("statusURLName", "URL danh mục đã tồn tại");
                return "category/createCategory";
            }else {
                redirectAttributes.addFlashAttribute("status", "Bài viết đã được lưu");
                categoryService.save(category);
                return "redirect:/list-category";
            }
        }
    }

    @GetMapping(value = "/edit-category")
    public ModelAndView viewEdit(@ModelAttribute("admin") User user, @RequestParam("id") Integer id){
        if ((user.getUserName() == null) || (user.getPassword() == null)){
            return new ModelAndView("error/error-404");
        }
        ModelAndView modelAndView = new ModelAndView("category/editCategory");
        modelAndView.addObject("category", categoryService.findById(id));
//        System.out.println(categoryService.findById(id));
        return modelAndView;
    }

    @PostMapping(value = "/edit-category")
    public String saveEdit(@Valid @ModelAttribute Category category, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, Model model, @ModelAttribute("admin") User user){
        if ((user.getUserName() == null) || (user.getPassword() == null)){
            return "error/error-404";
        }
        if (bindingResult.hasErrors()){
            return "category/editCategory";
        }else {
            if (categoryService.findById(category.getId()).getURLName().equals(category.getURLName())){
                redirectAttributes.addFlashAttribute("status", "Bài viết đã được cập nhập");
                categoryService.save(category);
                return "redirect:/list-category";
            }else {
                if (categoryService.existsByURLName(category.getURLName())){
                    model.addAttribute("statusURLName", "URL danh mục đã tồn tại");
                    model.addAttribute("category", category);
                    return "category/editCategory";
                }else {
                    redirectAttributes.addFlashAttribute("status", "Bài viết đã được cập nhập");
                    categoryService.save(category);
                    return "redirect:/list-category";
                }
            }
        }
    }

    @GetMapping(value = "/delete-category")
    public String delete(@ModelAttribute("admin") User user, @RequestParam("id") Integer id, RedirectAttributes redirectAttributes){
        if ((user.getUserName() == null) || (user.getPassword() == null)){
            return "error/error-404";
        }
        redirectAttributes.addFlashAttribute("status", "Bài viết đã được xoá");
        categoryService.delete(id);
        return "redirect:/list-category";
    }
}
