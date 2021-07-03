package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;

@Controller
public class ConverController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }

    @RequestMapping(value = "/convert", method = RequestMethod.GET)
    public ModelAndView convertMoney(@RequestParam double money) {
        ModelAndView modelAndView = new ModelAndView("index");
        double result = money * 23000;
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(result);
        modelAndView.addObject("result", moneyString);
        return modelAndView;
    }
}
