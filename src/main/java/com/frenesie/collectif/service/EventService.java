package com.frenesie.collectif.service;

import java.util.Optional;

import com.frenesie.collectif.model.Event;
import com.frenesie.collectif.repository.CrudRepository;


public interface EventService extends CrudRepository<Event> {

	Event update(int id);

	Optional<Event> getById(int id);

}
