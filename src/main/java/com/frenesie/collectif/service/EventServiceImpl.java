package com.frenesie.collectif.service;

import com.frenesie.collectif.controller.FrenesieAdviceController.ResourceNotFoundException;
import com.frenesie.collectif.model.Event;
import com.frenesie.collectif.repository.EventRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

	
    @Autowired
    private EventRepository eventRepository;
    
    @Override
    @Transactional
    public Event saveEvent(Event event) {
        logger.info("Création d'un nouvel événement");
        return eventRepository.save(event);
    }
    
    @Override
    @Transactional
    public Event updateEvent(Long id, Event eventDetails) {
        Event existingEvent = getEventById(id);
        // Mise à jour sélective avec validation
        if (eventDetails.getTitle() != null) {
            existingEvent.setTitle(eventDetails.getTitle());
        }
        if (eventDetails.getLocation() != null) {
            existingEvent.setLocation(eventDetails.getLocation());
        }
        if (eventDetails.getDescription() != null) {
            existingEvent.setDescription(eventDetails.getDescription());
        }
        if (eventDetails.getStartTime() != null) {
            existingEvent.setStartTime(eventDetails.getStartTime());
        }
        if (eventDetails.getEndTime() != null) {
            existingEvent.setEndTime(eventDetails.getEndTime());
        }
        if (eventDetails.getImageUrls() != null) {
            existingEvent.setImageUrls(eventDetails.getImageUrls());
        }
        if (eventDetails.getStatus() != null) {
            existingEvent.setStatus(eventDetails.getStatus());
        }
        
        logger.info("Mise à jour de l'événement avec l'ID : {}", id);
        return eventRepository.save(existingEvent);
    }
    
    @Override
    @Transactional
    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        logger.info("Suppression de l'événement avec l'ID : {}", id);
        eventRepository.delete(event);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Événement non trouvé avec l'ID : {}", id);
                return new ResourceNotFoundException("Event not found");
            });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Event> getUpcomingEvents(LocalDateTime now, Pageable pageable) {
        return eventRepository.findByStartTimeAfterOrderByStartTimeAsc(now, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Event> getPastEvents(LocalDateTime now, Pageable pageable) {
        return eventRepository.findByStartTimeBeforeOrderByStartTimeDesc(now, pageable);
    }

}
