package com.frenesie.collectif.controller;

import com.frenesie.collectif.model.Artist;
import com.frenesie.collectif.service.ArtistService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

   
    @GetMapping
    public String listArtists(Model model) {
        model.addAttribute("artists", artistService.findAllArtists());
        return "artists/list-artists"; // Vue qui affiche tous les artists
    }
    
    @GetMapping("/details/{id}")
    public String getArtistDetails(@PathVariable(name = "id") Integer artistId, Model model) {
        Artist artist = artistService.getArtistById(artistId);
        model.addAttribute("artist", artist);
        return "artists/artist-details";
    }
}
