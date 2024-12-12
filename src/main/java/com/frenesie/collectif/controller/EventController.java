package com.frenesie.collectif.controller;

import com.frenesie.collectif.model.Event;
import com.frenesie.collectif.service.EventService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventController {
	
    private EventService eventService;
    
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    
    @GetMapping("/list")
    public String listEvents(Model model) {
    	List<Event> events = eventService.getAll();
    	model.addAttribute("events", events);
        return "events/list";
    }

    @GetMapping("/details/{id}")
    public String getEventDetails(@PathVariable int id, Model model) {
        Optional<Event> eventOpt = eventService.getById(id);
        
        if(eventOpt.isEmpty()) {
        	throw new RuntimeException("L'évènement " + id + "n'a pas été trouvé.");
        }
        model.addAttribute("event", eventOpt.get());
        return "events/details";
    }


}