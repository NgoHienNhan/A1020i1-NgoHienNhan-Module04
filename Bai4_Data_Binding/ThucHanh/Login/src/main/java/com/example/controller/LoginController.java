package com.example.controller;

import com.example.dao.UserDao;
import com.example.model.Login;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"login",""})
public class LoginController {
    @GetMapping("/showLogin")
    public ModelAndView showCreate(){
        ModelAndView modelAndView =new ModelAndView("index");
        Login login=new Login();
        modelAndView.addObject("login",login);
        return modelAndView;
    }
    @Autowired
    UserDao userDao;
    @PostMapping("/dangnhap")
    public ModelAndView login(@ModelAttribute("login") Login login){
        User user= userDao.checkLogin(login);
        if(user==null){
            ModelAndView modelAndView=new ModelAndView("index");
            modelAndView.addObject("error","Lỗi khi đăng nhập");
            return  modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("reult");
            modelAndView.addObject("user",user);
            return modelAndView;
        }
    }
}
