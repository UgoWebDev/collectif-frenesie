package com.frenesie.collectif.controller;

import com.frenesie.collectif.model.Artiste;
import com.frenesie.collectif.model.Evenement;             
import com.frenesie.collectif.service.ArtisteService;      
import com.frenesie.collectif.service.EvenementService;   
import org.springframework.ui.Model;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

import java.util.List; 

@Controller
@Primary
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
        
        model.addAttribute("artistes", artistes);
        model.addAttribute("evenements", evenements);
        
        return "home";
    }
}