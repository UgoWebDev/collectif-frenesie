package com.frenesie.collectif.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frenesie.collectif.model.Artist;
import com.frenesie.collectif.model.Event;
import com.frenesie.collectif.service.ArtistService;
import com.frenesie.collectif.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final ArtistService artistService;
    private final EventService eventService;
//    private final SetService setService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        List<Artist> artists = artistService.findAllArtists();
        System.out.println("Artistes récupérés : " + artists);
        model.addAttribute("artists", artists);

        model.addAttribute("artists", artistService.findAllArtists());
        model.addAttribute("events", eventService.getAllEvents());
//        model.addAttribute("sets", setService.getAllSets());
        return "admin/dashboard";
    }

    @GetMapping("/artists/new")
    public String showCreateArtistPage(Model model) {
        model.addAttribute("artist", new Artist());
        return "admin/create-artist";
    }

    @PostMapping("/artists/create")
    public String createArtist(@ModelAttribute Artist artist) {
        artistService.createArtist(artist);
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/edit/{id}")
    public String editArtist(@PathVariable Integer id, Model model) {
        Artist artist = artistService.getArtistById(id);
        model.addAttribute("artist", artist);
        return "admin/edit-artist";
    }

    @PostMapping("/edit/{id}")
    public String updateArtist(@PathVariable Integer id, 
                                @Valid @ModelAttribute Artist artist, 
                                BindingResult result) {
        if (result.hasErrors()) {
            return "admin/edit-artist";
        }
        artist.setId(id);
        artistService.saveArtist(artist);
        return "redirect:/admin/dashboard";
    }
    
    @DeleteMapping("/artists/delete/{id}")
    public String deleteArtist(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        artistService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "L'artiste a été supprimé avec succès.");
        return "redirect:/admin/dashboard";
    }
    

    @GetMapping("/events/new")
    public String showCreateEventPage(Model model) {
        model.addAttribute("event", new Event());
        return "admin/create-event";
    }

    @PostMapping("/events/create")
    public String createEvent(@ModelAttribute Event event) {
        eventService.saveEvent(event);
        return "redirect:/admin/dashboard";
    }
}
