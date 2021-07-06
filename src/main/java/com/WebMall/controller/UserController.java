package com.WebMall.controller;

import com.WebMall.model.Coupon;
import com.WebMall.model.LoginUser;
import com.WebMall.model.Order;
import com.WebMall.model.User;
import com.WebMall.service.SecurityService;
import com.WebMall.service.userServices.UserService;
import com.WebMall.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    /**
     * @return register view
     * Registration page with data handling
     */
    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               HttpServletRequest request) {
        userValidator.validate(userForm, bindingResult);//validate user and find errors

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.createNewUser(userForm);//create new user in DB
        securityService.autoLogin(userForm.getEmail(), userForm.getPassword(), request);
        return "redirect:/";
    }

    /**
     * @return login view (custom login page)
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginUser());
        return "login";
    }

    /**
     * @return view of logout form
     */
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("/privatedetails")
    public String privateArea(Model model){
        User currentUser = userService.getLoggedUser();
        if (currentUser == null) return "404";

        List<Order> userOrders = currentUser.getOrders();
        List<Coupon> userCoupons = currentUser.getCoupons();

        model.addAttribute("ordersCount", currentUser.getOrdersCount());
        model.addAttribute("goodsBoughtCount", currentUser.getGoodsBoughtCount());
        model.addAttribute("bonusesCount", currentUser.getBonusesCount());
        model.addAttribute("userOrders", currentUser.getOrders());
        model.addAttribute("userCoupons", currentUser.getCoupons());

        return "private-area";
    }

}
