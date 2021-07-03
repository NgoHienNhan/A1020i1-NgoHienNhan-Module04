package com.example.customer.controller;

import com.example.customer.model.bean.Customer;
import com.example.customer.model.bean.Province;
import com.example.customer.model.service.CustomerService;
import com.example.customer.model.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ControllerCustomer {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

    @RequestMapping(value = "")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/customer/show", method = RequestMethod.GET)
    public ModelAndView showAll(@PageableDefault(value = 5) Pageable pageable) {
        Page<Customer> customers = customerService.findAllPage(pageable);
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("customerList",customers);
        if (customers.getContent().size() == 0){
            String msgData = "No data";
            modelAndView.addObject("msgData",msgData);
        }
        return modelAndView;
    }

    @GetMapping(value = "/customer/create")
    public String showCreatePage(Model model) {
        model.addAttribute("customer", new Customer());

        return "create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg", "Create customer :" + customer.getName() +
                " success.");
        customerService.save(customer);
        return "redirect:/customer/show";
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("msg", "Edit customer :" + customer.getName() +
                " success.");
        customerService.update(customer);
        return "redirect:/customer/show";
    }

    @GetMapping(value = "/delete/{id}/{name}")
    public String delete(@PathVariable Integer id,@PathVariable String name, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("msg", "Delete customer :" + name +
                " success.");
        customerService.remove(id);
        return "redirect:/customer/show";
    }

    @GetMapping(value = "/search")
    public ModelAndView search(@RequestParam("searchValue") String searchValue, @PageableDefault(value = 5) Pageable pageable){
        Page<Customer> customers = customerService.search(searchValue,pageable);
        ModelAndView modelAndView = new ModelAndView("showSearch");
        modelAndView.addObject("customerList",customers);
        if (customers.getContent().size() != 0){
            modelAndView.addObject("searchValue",searchValue);
        }else {
            String msgData = "No data";
            modelAndView.addObject("msgData",msgData);
        }
        return modelAndView;
    }
}
