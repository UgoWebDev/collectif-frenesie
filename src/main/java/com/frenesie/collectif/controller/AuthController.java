package com.frenesie.collectif.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frenesie.collectif.controller.dto.RegistrationDto;
import com.frenesie.collectif.service.UtilisateurService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
	
    private final UtilisateurService utilisateurService;

    public AuthController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    
    @GetMapping("/register")
    public String pageInscription(){
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("utilisateur") RegistrationDto dto,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "auth/register";
        }

        try {
            utilisateurService.register(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie");
            return "redirect:/home";
        } catch (RuntimeException e) {
            result.rejectValue("email", "error.user", e.getMessage());
            return "auth/register";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "auth/home";
    }
}
