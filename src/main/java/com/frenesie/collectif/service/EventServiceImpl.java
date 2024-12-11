package com.frenesie.collectif.service;

import com.frenesie.collectif.exception.ArtistNotFoundException;
import com.frenesie.collectif.exception.EventNotFoundException;
import com.frenesie.collectif.model.Event;
import com.frenesie.collectif.repository.EventRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
		
    private final EventRepository eventRepository;
    
    public EventServiceImpl(EventRepository eventRepository) {
    	this.eventRepository = eventRepository;
    }
    
    @Override
    public void add(Event event) {
    	eventRepository.add(event);
    }

    @Override
    public List<Event> getAll() {
    	return eventRepository.getAll();
    }
   
    public Optional<Event> getById(int id) {
    	return eventRepository.getById(id);
    }

    public void update(Event event) {
    	Optional<Event> eventOpt = getById();
    	if (eventOpt.isPresent()) {
    		eventRepository.update(event);
    	} else {
    		throw new EventNotFoundException();
    	}
    }
    
    public void delete(int id) {
        eventRepository.delete(id);
    }

}
