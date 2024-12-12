package com.frenesie.collectif.controller;

import java.util.List;
import java.util.Optional;

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
import com.frenesie.collectif.service.SetService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final ArtistService artistService;
    private final EventService eventService;
    private final SetService setService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        List<Artist> artists = artistService.getAll();
        System.out.println("Artistes récupérés : " + artists);
        model.addAttribute("artists", artists);

        model.addAttribute("artists", artistService.getAll());
        model.addAttribute("events", eventService.getAll());
        model.addAttribute("sets", setService.getAll());
        return "admin/dashboard";
    }

    @GetMapping("/artists/new")
    public String showCreateArtistPage(Model model) {
        model.addAttribute("artist", new Artist());
        return "admin/create-artist";
    }

    @PostMapping("/artists/create")
    public String createArtist(@ModelAttribute Artist artist) {
        artistService.add(artist);
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/edit/{id}")
    public String editArtist(@PathVariable Integer id, Model model) {
        Optional<Artist> artist = artistService.getById(id);
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
        artistService.update(artist);
        return "redirect:/admin/dashboard";
    }
    
    @DeleteMapping("/artists/delete/{id}")
    public String deleteArtist(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        artistService.delete(id);
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
        eventService.add(event);
        return "redirect:/admin/dashboard";
    }
}
