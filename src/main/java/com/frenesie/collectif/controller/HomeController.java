package com.frenesie.collectif.controller;

import com.frenesie.collectif.model.Artiste;
import com.frenesie.collectif.model.Artiste.Genre;
import com.frenesie.collectif.model.Evenement;             
import com.frenesie.collectif.service.ArtisteService;      
import com.frenesie.collectif.service.EvenementService;   
import org.springframework.ui.Model;                       
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

import java.util.List; 

@Controller
@RequiredArgsConstructor
public class HomeController {
	
    private final ArtisteService artisteService;
    private final EvenementService evenementService;
    
    // Constructeur explicite pour résoudre l'injection
    public HomeController(ArtisteService artisteService, EvenementService evenementService) {
        this.artisteService = artisteService;
        this.evenementService = evenementService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        // Récupérer les 3 derniers artistes
        List<Artiste> artistes = artisteService.findAllArtistes();
        
        // Récupérer les événements à venir
        List<Evenement> evenements = evenementService.getAllEvenements();
        
        model.addAttribute("artistes", artisteService.findArtistesByGenre(Genre.TECHNO));
        model.addAttribute("evenements", evenementService.getAllEvenements());
        
        return "home";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register";  // Renvoie la vue "register.html" pour l'inscription
    }
}