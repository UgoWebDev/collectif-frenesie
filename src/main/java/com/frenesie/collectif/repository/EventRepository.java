package com.frenesie.collectif.repository;


import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event> {


}