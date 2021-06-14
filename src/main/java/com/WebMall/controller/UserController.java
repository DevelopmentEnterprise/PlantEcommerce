package com.WebMall.controller;

import com.WebMall.model.LoginUser;
import com.WebMall.model.User;
import com.WebMall.service.SecurityService;
import com.WebMall.service.UserService;
import com.WebMall.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
        securityService.autoLogin(userForm.getFirstName(), userForm.getPasswordConfirm());
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
