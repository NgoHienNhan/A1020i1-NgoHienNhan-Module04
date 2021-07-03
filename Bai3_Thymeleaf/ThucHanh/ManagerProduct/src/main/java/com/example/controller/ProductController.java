package com.example.controller;

import com.example.model.bean.Product;
import com.example.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("listProduct", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/showCreate")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("msg", "Add product " + product.getName() + " success");
        return "redirect:/";
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable int id){
        ModelAndView modelAndView =new ModelAndView("edit");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Product product, RedirectAttributes redirectAttributes){
        productService.update(product.getId(),product);
        redirectAttributes.addFlashAttribute("msg", "Update product with Id:" + product.getId()
                + " success");
        return "redirect:/";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id,RedirectAttributes redirectAttributes){
        String temp= productService.findById(id).getName();
        productService.remove(id);
        redirectAttributes.addFlashAttribute("msg", "Delete product :" +temp
                + " success");
        return "redirect:/";
    }
}
