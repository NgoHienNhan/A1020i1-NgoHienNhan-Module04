package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginLogoutController {
    @RequestMapping(value = "/")
    public String showPageLogin(){
        return "login";
    }

    @GetMapping(value = "/home")
    public String showPageHome(){
        return "index";
    }
}
