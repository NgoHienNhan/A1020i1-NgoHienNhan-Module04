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
@RequestMapping(value = "/province")
public class ControllerProvince {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/show")
    public ModelAndView showAll(@PageableDefault(value = 5) Pageable pageable) {
        Page<Province> provinces = provinceService.findAllPage(pageable);
        ModelAndView modelAndView = new ModelAndView("showProvince");
        modelAndView.addObject("provinceList",provinces);
        if (provinces.getContent().size() == 0){
            String msgData = "No data";
            modelAndView.addObject("msgData",msgData);
        }
        return modelAndView;
    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Province province = provinceService.findById(id);

        Iterable<Customer> customers = customerService.findAllByProvince(province);

        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("province", province);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public String showCreatePage(Model model) {
        model.addAttribute("province", new Province());

        return "createProvince";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Province province, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg", "Create province :" + province.getName() +
                " success.");
        provinceService.save(province);
        return "redirect:/province/show";
    }

    @GetMapping(value = "/delete/{id}/{name}")
    public String delete(@PathVariable Long id, @PathVariable String name, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("msg", "Delete customer :" + name +
                " success.");
        provinceService.remove(id);
        return "redirect:/province/show";
    }
}
