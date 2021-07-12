package com.example.demo.controller;


import com.example.demo.bean.Product;
import com.example.demo.bean.ProductList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Controller
@SessionAttributes("Products")
public class ProductController {

    @ModelAttribute("productArrayList")
    public ArrayList<Product> setProductList(){
        Product product = new Product(1,"sp1","giohoachucmung017.jpg",5000);
        Product product2 = new Product(2,"sp2","140.jpg.300.jpg",5000);
        Product product3 = new Product(3,"sp3","5062_gio-hoa-hong.jpg",5000);

        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(product);
        productArrayList.add(product2);
        productArrayList.add(product3);
        return productArrayList;
    }


    @ModelAttribute("products")
    public ProductList setProduct(){
        return new ProductList();
    }

    @GetMapping("/")
    public String showPageIndex (){
        return "index";
    }

    @GetMapping("/add/{name}/{price}/{linkImg}")
    public String addSession(@PathVariable("name") String name,
                             @PathVariable("price") Integer price,
                             @PathVariable("linkImg") String linkImg,
                             @ModelAttribute("products") ProductList products,
                             RedirectAttributes redirectAttributes){
        Product product = new Product();
        product.setNameProduct(name);
        product.setPrice(price);
        product.setLinkImg(linkImg);
        products.add(product);

        redirectAttributes.addFlashAttribute("products",products);
        return "index";
    }
}
