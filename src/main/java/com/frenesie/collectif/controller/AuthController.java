package com.frenesie.collectif.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frenesie.collectif.controller.dto.RegistrationDto;
import com.frenesie.collectif.model.User;
import com.frenesie.collectif.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
	
    private final UserService userService;
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") RegistrationDto dto,
                            BindingResult result,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "auth/register";
        }

        try {
            userService.register(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie");
            return "redirect:/home";
        } catch (RuntimeException e) {
            result.rejectValue("email", "error.user", e.getMessage());
            return "auth/register";
        }
    }

    @GetMapping("/login")
    public String login(
        @RequestParam(required = false) String error,
        @RequestParam(required = false) String logout,
        Model model
    ) {
        if (error != null) {
            model.addAttribute("error", "Vos identifiants sont invalides.");
        }
        if (logout != null) {
            model.addAttribute("message", "Vous avez été déconnecté avec succès.");
        }
        return "auth/login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        try {
            User user = userService.authenticate(email, password);
            session.setAttribute("currentUser", user);
            System.out.println("Utilisateur connecté : " + user.getEmail());
            redirectAttributes.addFlashAttribute("successMessage", "Connexion réussie !");
            return "redirect:/artists";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/login";
        }
    }

}
