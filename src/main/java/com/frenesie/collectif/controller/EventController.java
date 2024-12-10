package com.frenesie.collectif.controller;

import com.frenesie.collectif.controller.dto.EventDto;
import com.frenesie.collectif.model.Event;
import com.frenesie.collectif.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
	
    @Autowired
    private EventService eventService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    // Convertit un Event en EventDto
    private EventDto convertToDto(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

//    // Convertit un EventDto en Event
//    private Event convertToEntity(EventDto eventDto) {
//        return modelMapper.map(eventDto, Event.class);
//    }
    
    @GetMapping
    public String events(Model model) {
    	model.addAttribute("events", eventService.getAllEvents());
    	return "events";
    }
    
    @GetMapping("/list")
    public String listEvents(Model model) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Event> events = eventService.getUpcomingEvents(LocalDateTime.now(), pageable);
        model.addAttribute("events", events);
        return "events/list";
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody Event event) {
        Event savedEvent = eventService.saveEvent(event);
        EventDto eventDto = convertToDto(savedEvent);
        return new ResponseEntity<>(eventDto, HttpStatus.CREATED);
    }

    @GetMapping("/details/{id}")
    public String getEventDetails(@PathVariable(name = "id") Long eventId, Model model) {
        Event event = eventService.getEventById(eventId);
        model.addAttribute("event", event);
        return "events/details";
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(
        @PathVariable Long id, 
        @Valid @RequestBody Event eventDetails
    ) {
        Event updatedEvent = eventService.updateEvent(id, eventDetails);
        EventDto eventDto = convertToDto(updatedEvent);
        return ResponseEntity.ok(eventDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}