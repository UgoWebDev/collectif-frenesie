package com.frenesie.collectif.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

	    void add(T entity);

	    public List<T> getAll();

	    Optional<T> getById(int id);

	    void update(T entity);

	    void delete(int id);
	
}
