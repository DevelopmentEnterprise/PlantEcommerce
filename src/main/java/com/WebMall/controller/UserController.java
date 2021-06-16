package com.WebMall.controller;

import com.WebMall.model.LoginUser;
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
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        String password = userForm.getPassword();

        userService.save(userForm);

        securityService.autoLogin(userForm.getEmail(), password, request);
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
}
