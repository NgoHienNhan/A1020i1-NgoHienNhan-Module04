package com.example.demo.controller;


import com.example.demo.bean.Cart;
import com.example.demo.bean.Product;
import com.example.demo.bean.ProductBuy;
import com.example.demo.service.ProductBuyService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductBuyService productBuyService;

    @ModelAttribute("cart")
    public HashMap<Long, Cart> setProduct() {
        return new HashMap<Long, Cart>();
    }

    @GetMapping("/")
    public String showPageIndex(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String showDetailProduct(@PathVariable("id") Integer id,
                                    @SessionAttribute("cart") HashMap<Integer, Cart> cartHashMap,
                                    Model model){
        model.addAttribute("product",productService.findById(id));
        model.addAttribute("cartNum",cartHashMap.size());
        return "detail";
    }


    @GetMapping("/add/{id}")
    public String addSession(@PathVariable("id") Integer id,
                             @ModelAttribute("cart") HashMap<Integer, Cart> cartHashMap,
                             Model model) {
        if (cartHashMap == null){
            cartHashMap = new HashMap<>();
        }
            Product product = productService.findById(id);
        if (product!=null){
            Cart cart;
            if (cartHashMap.containsKey(id)){
                cart = cartHashMap.get(id);
                cart.setProduct(product);
                cart.setQuantity(cart.getQuantity()+1);
            }else {
                cart = new Cart();
                cart.setProduct(product);
                cart.setQuantity(1);
            }
            cartHashMap.put(id, cart);
        }

        model.addAttribute("products", productService.findAll());
        model.addAttribute("cartNum",cartHashMap.size());
        model.addAttribute("cart", cartHashMap);
        return "index";
    }

    @GetMapping(value = "/cart")
    public String showCart(@SessionAttribute("cart") HashMap<Integer, Cart> cartHashMap,
                           Model model){
        model.addAttribute("carts",cartHashMap);
        model.addAttribute("total",totalPrice(cartHashMap));
        return "cart";
    }

    @GetMapping(value = "/buy/{id}")
    public String buy(@PathVariable("id") Integer id,
                      @SessionAttribute("cart") HashMap<Integer, Cart> cartHashMap,
                      Model model){
        Cart cart = cartHashMap.get(id);
        model.addAttribute("cart",cart);
        return "buy";
    }

    @PostMapping(value = "/buy")
    public String buyPost(@SessionAttribute("cart") HashMap<Integer, Cart> cartHashMap,
                          @RequestParam("quantity") Integer quantity,
                          @RequestParam("total") Integer total,
                          @RequestParam("nameProduct") String nameProduct,
                          @RequestParam("id") Integer id,
                          Model model){
        ProductBuy productBuy = new ProductBuy(id,nameProduct,quantity,total);
        if (cartHashMap != null){
            cartHashMap.remove(id);
        }else {
            cartHashMap = new HashMap<>();
        }
        productBuyService.save(productBuy);
        model.addAttribute("cart",cartHashMap);
        model.addAttribute("cartNum",cartHashMap.size());
        return "index";
    }

    public Integer totalPrice(HashMap<Integer, Cart> cartHashMap){
        int total = 0;
        for (Map.Entry<Integer, Cart> list : cartHashMap.entrySet()){
            total += list.getValue().getProduct().getPrice()* list.getValue().getQuantity();
        }
        return total;
    }
}
