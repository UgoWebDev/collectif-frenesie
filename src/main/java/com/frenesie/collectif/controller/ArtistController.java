package com.frenesie.collectif.controller;

import com.frenesie.collectif.model.Artist;
import com.frenesie.collectif.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/add-artist")
    public String showAddArtistForm(Model model) {
        model.addAttribute("artist", new Artist());
        return "artists/add-artist";
    }
    
    @PostMapping("/add-artist")
    public String addArtist(@ModelAttribute Artist artist) {
        Artist savedArtist = artistService.saveArtist(artist);
        return "redirect:/artists/" + savedArtist.getId(); // Rediriger vers la page de l'artiste
    }
    
    @GetMapping
    public String listArtists(Model model) {
        model.addAttribute("artists", artistService.getAllArtists());
        return "artists/list-artists"; // Vue qui affiche tous les artistes
    }

    @GetMapping("/details/{id}")
    public String viewArtistDetails(@PathVariable Integer id, Model model) {
        artistService.getArtistById(id).ifPresent(artist -> model.addAttribute("artist", artist));
        return "artists/artist-details"; // Vue qui affiche le détail de l'artiste
    }
}
