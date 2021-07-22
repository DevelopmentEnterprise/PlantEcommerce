package com.WebMall.controller;

import com.WebMall.model.CartItem;
import com.WebMall.model.LoginUser;
import com.WebMall.model.User;
import com.WebMall.service.AccessDeniedException;
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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * -- Created by Killer_hacker999 --
 *
 * Represents all functionality for operating user logic:
 * authentication, registration...
 */
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
     * @return register layout
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
        if (!userService.checkAuthUser())
            throw new AccessDeniedException();

        User loggedUser = userService.getLoggedUser();
        model.addAttribute("user", loggedUser);

        return "private-area";
    }

    @RequestMapping("/cart")
    public String showUserCart(Model model){
        if (!userService.checkAuthUser()){
            model.addAttribute("cart", new ArrayList<>());
            return "cart";
        }

        List<CartItem> userCart = userService.getLoggedUser().getCart();
        model.addAttribute("cart", userCart);
        return "cart";
    }

}
