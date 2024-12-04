package com.frenesie.collectif.service;

import com.frenesie.collectif.model.Event;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EventService {
    Event createEvent(Event event);
    Event getEventById(Long id);
    List<Event> getAllEvents();
    Event updateEvent(Long id, Event eventDetails);
    void deleteEvent(Long id);
}
