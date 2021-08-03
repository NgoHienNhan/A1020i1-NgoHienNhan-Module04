package com.example.demo.controller;

import com.example.demo.bean.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/")
    public ModelAndView listAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return new ModelAndView("index","customers",customers);
    }

    @GetMapping(value = "create")
    public ModelAndView showPageCreate(){
        return new ModelAndView("create","customer",new Customer());
    }

    @PostMapping(value = "/create")
    public String createCustomer(@ModelAttribute Customer customer) {
        long id = (long) (Math.random() * 10);
        customer.setId(id);
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView showPageUpdate(@PathVariable("id") long id){
        return new ModelAndView("update","customer",customerService.findById(id));
    }

    @PostMapping(value = "/update")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping(value = "/customers/{id}")
    public String deleteCustomer(@PathVariable("id") long id) {
        customerService.remove(id);
        return "redirect:/";
    }
}
