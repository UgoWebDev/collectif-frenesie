package com.frenesie.collectif.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frenesie.collectif.controller.dto.RegistrationDto;
import com.frenesie.collectif.model.User;
import com.frenesie.collectif.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
	
    private final UserService userService;
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationDto());
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
