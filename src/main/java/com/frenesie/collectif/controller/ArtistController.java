package com.frenesie.collectif.controller;

import com.frenesie.collectif.model.Artist;
import com.frenesie.collectif.service.ArtistService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }
   
    @GetMapping
    public String listArtists(Model model) {
    	List<Artist> artists = artistService.getAll();
        model.addAttribute("artists", artists);
        return "artists/list-artists";
    }
    
    @GetMapping("/details/{id}")
    public String getArtistDetails(@PathVariable(name = "id") int id, Model model) {
        Optional<Artist> artist = artistService.getById(id);
        model.addAttribute("artist", artist);
        return "artists/artist-details";
    }
}
