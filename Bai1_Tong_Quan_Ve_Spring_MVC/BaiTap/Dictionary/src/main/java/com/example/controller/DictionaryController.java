package com.example.controller;

import com.sun.javafx.collections.MappingChange;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class DictionaryController {
    @RequestMapping(value = "/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @RequestMapping(value = "/convert", method = RequestMethod.GET)
    public ModelAndView convert(@RequestParam String word) {
        boolean check = false;
        String result = "";
        ModelAndView modelAndView = new ModelAndView("index");
        Map<String, String> listWord = new TreeMap<>();
        listWord.put("Hello", "Xin chào");
        listWord.put("Handsome", "Đẹp trai");
        listWord.put("Cool", "Lạnh lùng");
       for(int i=0;i<listWord.size();i++){
           if(word.contains((CharSequence) listWord.keySet().toArray()[i])){
               result=listWord.get((listWord.keySet().toArray()[i]));
               check=true;
               break;
           }
       }
        if (!check)
            result = "Không tìm thấy";
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
