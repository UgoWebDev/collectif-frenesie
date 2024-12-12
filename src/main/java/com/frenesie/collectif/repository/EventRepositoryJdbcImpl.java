package com.frenesie.collectif.repository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.Event;

@Repository
public class EventRepositoryJdbcImpl implements EventRepository {
	Logger logger = LoggerFactory.getLogger(EventRepositoryJdbcImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final JdbcTemplate jdbcTemplate;
	
	public EventRepositoryJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}


    @Override
    public void add(Event event) {
    	String sql = "INSERT INTO events (title, location, description, start_time as startTime, end_time as endTime, status)" + 
    "VALUES (:title, :location, :description, :startTime, :endTime, :status)";
    	namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(event));
    }
    
    @Override
    public List<Event> getAll() {
    	String sql = "SELECT * FROM events";
    	List<Event> events = namedParameterJdbcTemplate.query(sql, 
    			new BeanPropertyRowMapper<>(Event.class));
    	return events;
    }
    
    @Override
    public Optional<Event> getById(int id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        Event event = null;
        try {
            event = jdbcTemplate.queryForObject(sql,
            		new BeanPropertyRowMapper<>(Event.class), id);
        } catch (DataAccessException exc) {
			exc.printStackTrace();
			logger.warn(exc.getMessage());
        }
		return Optional.ofNullable(event);
    }

    @Override
    public void update(Event event) {
        String sql = "UPDATE events SET title=:title, location=:location, description=:description, "
        		+ "start_time=:startTime, endd_time=:endTime, status=:status "
        		+ " WHERE id=:id";
        int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(event));
        if (nbRows !=1) {
        	throw new RuntimeException("L'évènement n'a pas pu être mis à jour" + event);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM events WHERE id = ?";
        int nbRows = jdbcTemplate.update(sql, id);
        if(nbRows !=1) {
        	throw new RuntimeException("L'évènement n'a pas pu être supprimé, id = " + id);
        }
    }
}
