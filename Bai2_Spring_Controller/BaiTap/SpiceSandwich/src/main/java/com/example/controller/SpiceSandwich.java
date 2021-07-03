package com.example.controller;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SpiceSandwich {
    @GetMapping("/")
    public ModelAndView homePage(){
        return new ModelAndView("index");
    }
    @PostMapping("/spice")
    public ModelAndView spice(@RequestParam String[] spice){
        ModelAndView modelAndView=new ModelAndView("index");
        if(spice.length==0)
            modelAndView.addObject("msg","you need to choose at least one spice");
        List<String> listSpice = new ArrayList<>();
        for (String sp:spice) {
            listSpice.add(sp);
        }
        modelAndView.addObject("listSpice",listSpice);
        return modelAndView;
    }
}
