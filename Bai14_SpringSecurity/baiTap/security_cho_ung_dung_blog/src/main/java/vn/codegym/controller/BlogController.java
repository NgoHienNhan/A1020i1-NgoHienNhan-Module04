package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.model.Blog;
import vn.codegym.model.Category;
import vn.codegym.model.User;
import vn.codegym.service.BlogService;
import vn.codegym.service.CategoryService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService service;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("listCategory")
    public List<Category> listCategory(){
        return categoryService.findAll();
    }

    @GetMapping(value = "/")
    public String home(@PageableDefault(2) Pageable pageable, Model model, HttpServletRequest request){
        Page<Blog> page = service.findAll(pageable);
        model.addAttribute("listPost", page);
        model.addAttribute("listDate", this.getAllDate(page));
        model.addAttribute("pages", new int[page.getTotalPages()]);
        model.addAttribute("home", true);
        return "blog/index";
    }

//    @GetMapping(value = "/blog")
//    public String listBlog(@PageableDefault(2) Pageable pageable, Model model){
//        Page<Blog> page = service.findAll(pageable);
//
//        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
//        for (Blog blog : page){
//            blog.setDateDisplay(formatter.format(blog.getDate()));
//            blog.getCategory().setBlogs(null);
//        }
//        model.addAttribute("listPost", page);
//        return "fragments/listBlog";
//    }

    @GetMapping(value = "/create-blog")
    public String viewCreate(Model model){
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @PostMapping(value = "/create-blog")
    public String saveCreate(@Valid @ModelAttribute Blog blog, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            return "blog/create";
        }else {
            if (service.existsByURLTitle(blog.getURLTitle())){
                model.addAttribute("statusURLTitle", "URL tiêu đề đã tồn tại");
                return "blog/create";
            }else {
                redirectAttributes.addFlashAttribute("status", "Bài viết đã được lưu");
                service.save(blog);
                return "redirect:/";
            }
        }
    }

    @GetMapping(value = "/{category}")
    public String homeTips(@PathVariable String category, Model model, @PageableDefault(3) Pageable pageable){
        if (!categoryService.existsByURLName(category)){
            return "error/error-404";
        }
        Page<Blog> page = service.findAllByCategory_URLName(category, pageable);
        model.addAttribute("selectCategory", category);
        model.addAttribute("listPost", page);
        model.addAttribute("listDate", this.getAllDate(page));
        model.addAttribute("pages", new int[page.getTotalPages()]);
        return "blog/index";
    }

    @GetMapping(value = "/view/{category}/{title}")
    public String view(@PathVariable String category, @PathVariable String title, Model model, HttpServletResponse response,
                       HttpServletRequest request){
        Blog blog = service.findByURLTitle(title);
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        if ((blog == null) || (!categoryService.existsByURLName(category))){
            return "error/error-404";
        }
        if (!getStatusView(title, request)){
            blog.setViews(blog.getViews()+1);
            service.save(blog);
            Cookie cookie = new Cookie(title, "true");
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
        }
        model.addAttribute("selectCategory", category);
        model.addAttribute("blog", blog);
        model.addAttribute("date", format.format(blog.getDate()));
        return "blog/viewBlog";
    }

    @GetMapping(value = "/edit-blog/{id}")
    public ModelAndView viewEdit(@PathVariable Integer id){
        return new ModelAndView("blog/edit", "blog", service.findById(id));
    }

    @GetMapping(value = "/edit-blog")
    public String errorEdit(){
        return "error/error-404";
    }

    @PostMapping(value = "/edit-blog")
    public String saveEdit(@Valid @ModelAttribute Blog blog, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            return "blog/edit";
        }else {
            if (service.findById(blog.getId()).getURLTitle().equals(blog.getURLTitle())){
                redirectAttributes.addFlashAttribute("status", "Bài viết đã được cập nhập");
                service.save(blog);
                return "redirect:/";
            }else {
                if (service.existsByURLTitle(blog.getURLTitle())){
                    model.addAttribute("statusURLTitle", "URL tiêu đề đã tồn tại");
                    model.addAttribute("blog", blog);
                    return "blog/edit";
                }else {
                    redirectAttributes.addFlashAttribute("status", "Bài viết đã được cập nhập");
                    service.save(blog);
                    return "redirect:/";
                }
            }

        }
    }

    @GetMapping(value = "/delete-blog")
    public String delete(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("status", "Bài viết đã được xoá");
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping(value = "/search")
    public ModelAndView viewSearch(@RequestParam("value") String value, @PageableDefault(2) Pageable pageable){
        Page<Blog> page = service.findAllByTitleContaining(value, pageable);
        ModelAndView modelAndView = new ModelAndView("blog/index");
        modelAndView.addObject("listPost", page);
        modelAndView.addObject("listDate", this.getAllDate(page));
        modelAndView.addObject("pages", new int[page.getTotalPages()]);
        return modelAndView;
    }


    @GetMapping(value = "/about")
    public ModelAndView viewAbout(){
        return new ModelAndView("blog/about", "about", true);
    }

    @GetMapping(value = "/login")
    public String viewLogin(Model model){
        return "blog/login";
    }

    @GetMapping(value = "/loginFail")
    public String loginFail(Model model){
        model.addAttribute("message", "Đăng nhập thất bại");
        return "blog/login";
    }

//    @PostMapping(value = "/login")
//    public String checkLogin(@ModelAttribute User user, @ModelAttribute("admin") User admin,
//                             @RequestParam(value = "rememberMe", defaultValue = "off") String rememberMe, Model model,
//                             HttpServletResponse response){
//        if (user.getUserName().equals("ledung.ht17@gmail.com") && user.getPassword().equals("LeDung123")){
//            admin.setPassword(user.getPassword());
//            admin.setUserName(user.getUserName());
//            this.role = "admin";
////            if (rememberMe.equals("on")) {
////                Cookie cookie = new Cookie("user", user.getUserName());
////                cookie.setMaxAge(5 * 60);
////                cookie.setPath("/");
////                response.addCookie(cookie);
////                cookie = new Cookie("pass", user.getPassword());
////                cookie.setMaxAge(5 * 60);
////                cookie.setPath("/");
////                response.addCookie(cookie);
////            }
//            return "redirect:/";
//        }else {
//            model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu");
//            model.addAttribute("user", new User());
//            return "blog/login";
//        }
//    }


    private List<String> getAllDate(Page<Blog> list) {
        List<String> listDate = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        for (Blog blog : list){
            listDate.add(formatter.format(blog.getDate()));
        }
        return listDate;
    }

    private boolean getStatusView(String title, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie ck : cookies){
                if (ck.getName().equals(title)){
                    if (ck.getValue().equals("true")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
