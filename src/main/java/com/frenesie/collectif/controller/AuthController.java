package com.frenesie.collectif.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.frenesie.collectif.model.User;
import com.frenesie.collectif.service.CrudService;
import com.frenesie.collectif.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
    
    private CrudService<User> userService;

	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
    	model.addAttribute("body", "auth/register");
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid User user, BindingResult bindingResult) {
    	model.addAttribute("body", "auth/register");
    	if(bindingResult.hasErrors()) {
    		return "home";
    	}
    	userService.save(user);
    	return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

}
