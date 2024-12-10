package com.frenesie.collectif.service;

import com.frenesie.collectif.model.Event;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    Event saveEvent(Event event);
    Event updateEvent(Long id, Event eventDetails);
    void deleteEvent(Long id);
    Event getEventById(Long id);
    List<Event> getAllEvents();
    Page<Event> getUpcomingEvents(LocalDateTime now, Pageable pageable);
    Page<Event> getPastEvents(LocalDateTime now, Pageable pageable);
}
