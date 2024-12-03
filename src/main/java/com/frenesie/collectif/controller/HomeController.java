package com.frenesie.collectif.controller;

import com.frenesie.collectif.model.Artist;
import com.frenesie.collectif.model.Event;
import com.frenesie.collectif.service.ArtistService;      
import com.frenesie.collectif.service.EventService;   
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
	
    private final ArtistService artistService;
    private final EventService eventService;
    
    @GetMapping("/home")
    public String home(Model model) {
        // Récupérer les 3 derniers artistes
        List<Artist> artists = artistService.findAllArtists();
        
        // Récupérer les événements à venir
        List<Event> events = eventService.getAllEvents();
        
        model.addAttribute("artistes", artists);
        model.addAttribute("events", events);
        
        return "home";
    }
}