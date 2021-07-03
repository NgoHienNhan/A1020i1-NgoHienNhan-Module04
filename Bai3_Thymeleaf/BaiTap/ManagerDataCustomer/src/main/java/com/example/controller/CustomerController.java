package com.example.controller;

import com.example.model.bean.Customer;
import com.example.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("listCustomer", customerService.findAll());
        return modelAndView;
    }

    @GetMapping("/showCreate")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("msg", "Add customer " + customer.getName() + " success");
        return "redirect:/";
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable int id){
        ModelAndView modelAndView =new ModelAndView("edit");
        modelAndView.addObject("customer",customerService.findById(id));
        return modelAndView;
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Customer customer,RedirectAttributes redirectAttributes){
        customerService.update(customer.getId(),customer);
        redirectAttributes.addFlashAttribute("msg", "Update customer with Id:" + customer.getId()
                + " success");
        return "redirect:/";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id,RedirectAttributes redirectAttributes){
        String temp=customerService.findById(id).getName();
        customerService.remove(id);
        redirectAttributes.addFlashAttribute("msg", "Delete customer :" +temp
                + " success");
        return "redirect:/";
    }
}
