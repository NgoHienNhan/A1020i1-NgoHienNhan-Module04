package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import vn.codegym.model.Category;
import vn.codegym.model.User;
import vn.codegym.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes("admin")
public class HandleErrorController implements ErrorController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("admin")
    public User admin(){
        return new User();
    }

    @ModelAttribute("listCategory")
    public List<Category> listCategory(){
        return categoryService.findAll();
    }

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/error-500";
            }
        }
        return "error/error";
    }
}
