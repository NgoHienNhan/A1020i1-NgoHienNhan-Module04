package com.example.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeZoneController {
    @GetMapping("/")
    public ModelAndView homePage(){
        return new ModelAndView("index");
    }
    @GetMapping("/worldclock")
    public ModelAndView timeZone(@RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city){
        ModelAndView modelAndView=new ModelAndView("index");
        Date date = new Date();
// Get timezone by the local
        TimeZone local = TimeZone.getDefault();
// Get timezone by the specified city
        TimeZone locale = TimeZone.getTimeZone(city);
        long locale_time = date.getTime() +
                (locale.getRawOffset() - local.getRawOffset());
// Reset the date by locale_time
        date.setTime(locale_time);
        modelAndView.addObject("city", city);
        modelAndView.addObject("date", date);
        return modelAndView;
    }
}
