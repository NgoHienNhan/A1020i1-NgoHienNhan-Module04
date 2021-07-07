package com.example.demo.controller;

import com.example.demo.bean.UserItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;

@Controller
@SessionAttributes("user")
public class LoginLogoutController {
    @RequestMapping(value = "/")
    public String showPageLogin(@CookieValue(value = "testCookie", defaultValue = "defaultCookieValue") String cookieValue, Model model){
        System.out.println(cookieValue);
        model.addAttribute("cookieValue", cookieValue);
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHomePage(@ModelAttribute("user") UserItem user, RedirectAttributes attributes, Principal principal,
                               Model model,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();
        user.setUserName(userName);
        attributes.addFlashAttribute("user",user);

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies)
                    .forEach(c -> System.out.println(c.getName() + "=" + c.getValue()));
        }

        Cookie newCookie = new Cookie("testCookie", userName);
        newCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(newCookie);

        return "index";
    }

    @ModelAttribute("user")
    public UserItem userItem(){
        return new UserItem();
    }

}
