package com.frenesie.collectif.service;

import com.frenesie.collectif.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> getAllEvents();
    Optional<Event> getEventById(Long id);
    Event saveEvent(Event evenement);
    void deleteEvent(Long id);
}
