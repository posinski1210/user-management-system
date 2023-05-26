package com.ums.controller;

import com.ums.entity.User;
import com.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {
    private UserService userService;

    @Autowired
    public RegisterController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "register";
        }
        if(userService.isUserEmailPresent(user.getEmail())){
            model.addAttribute("exist",true);
            return "register";
        }
        userService.createUser(user);
        return "success";
    }
}
