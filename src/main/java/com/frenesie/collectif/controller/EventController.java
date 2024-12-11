package com.frenesie.collectif.controller;

import com.frenesie.collectif.model.Event;
import com.frenesie.collectif.service.EventService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
	
    @Autowired
    private EventService eventService;
    
    @GetMapping("/list")
    public String listEvents(Model model) {
    	List<Event> events = eventService.getAll();
    	model.addAttribute("events", events);
        return "events/list";
    }

    @GetMapping("/details/{id}")
    public String getEventDetails(@PathVariable("id") int id, Model model) {
        Optional<Event> eventOpt = eventService.getById(id);
        
        if(eventOpt.isEmpty()) {
        	throw new RuntimeException("L'évènement " + id + "n'a pas été trouvé.");
        }
        model.addAttribute("event", eventOpt.get());
        return "events/details";
    }


}