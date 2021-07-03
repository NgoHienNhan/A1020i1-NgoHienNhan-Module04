package com.example.demo.controller;

import com.example.demo.bean.Customer;
import com.example.demo.bean.RentType;
import com.example.demo.bean.Service;
import com.example.demo.bean.ServiceType;
import com.example.demo.service.RentTypeService;
import com.example.demo.service.ServiceService;
import com.example.demo.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping(value = "/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @Autowired
    private RentTypeService rentTypeService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @ModelAttribute("rentType")
    public Iterable<RentType> rentTypes() {
        return rentTypeService.findAll();
    }

    @ModelAttribute("serviceType")
    public Iterable<ServiceType> serviceTypes() {
        return serviceTypeService.findAll();
    }

    @GetMapping(value = "/showAll")
    public ModelAndView showAll(@PageableDefault(value = 9) Pageable pageable) {
        Page<Service> services = serviceService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("service/showAllService");
        if (services.getContent().size() == 0) {
            modelAndView.addObject("msg", "chua co dich vu nao.");
            return modelAndView;
        }
        modelAndView.addObject("services", services);
        return modelAndView;
    }

    @GetMapping(value = "/showDetail/{id}")
    public String showDetail(@PathVariable Integer id, Model model) {
        Service service = serviceService.findById(id);
        model.addAttribute("service", service);
        return "service/showDetailService";
    }

    @GetMapping(value = "/create")
    public String showPageCreateService(Model model) {
        model.addAttribute("service", new Service());
        return "service/createService";
    }

    @PostMapping(value = "/create")
    public String save(@ModelAttribute Service service, @RequestParam("img") MultipartFile img, RedirectAttributes redirectAttributes) {
        if (img.isEmpty()){
            service.setLinkImg("");
        }
        Path path = Paths.get("upload/");
        try{
            InputStream inputStream = img.getInputStream();
            Files.copy(inputStream, path.resolve(img.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            service.setLinkImg(img.getOriginalFilename().toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("msg", "Create service : " + service.getServiceName() + " success.");
        serviceService.save(service);
        return "redirect:/service/showAll";
    }

    @GetMapping(value = "/update/{id}")
    public String showPageUpdate(@PathVariable Integer id, Model model) {
        Service service = serviceService.findById(id);
        model.addAttribute("service", service);
        return "service/updateService";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute Service service, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg", "Update service : " + service.getServiceName() + " success.");
        serviceService.save(service);
        return "redirect:/service/showAll";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Service service = serviceService.findById(id);
        redirectAttributes.addFlashAttribute("msg", "Delete customer: " + service.getServiceName() + "success.");
        serviceService.delete(service);
        return "redirect:/service/showAll";
    }
}
