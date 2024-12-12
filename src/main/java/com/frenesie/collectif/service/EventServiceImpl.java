package com.frenesie.collectif.service;

import com.frenesie.collectif.model.Event;
import com.frenesie.collectif.repository.EventRepository;

import org.springframework.stereotype.Service;

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

    @Override
    public Optional<Event> getById(int id) {
        return eventRepository.getById(id);
    }

    @Override
    public void update(Event event) {
    	Optional<Event> eventOpt = getById(event.getId());
    	if ( eventOpt.isPresent()) {
    		eventRepository.update(event);
    	} else{
    		throw new RuntimeException();
    	}
    }

    @Override
    public void delete(int id) {
        eventRepository.delete(id);
    }

	@Override
	public void save(Event entity) {
		
		if(entity.getId()==null) {
			this.add(entity);
			return;
		}
		this.update(entity);
		
	}

    
}
