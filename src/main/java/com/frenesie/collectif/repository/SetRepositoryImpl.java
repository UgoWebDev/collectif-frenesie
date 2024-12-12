package com.frenesie.collectif.repository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.Set;

@Repository
@Primary
public class SetRepositoryImpl implements SetRepository {
	
	private static int idxSet = 1;
	private List<Set> sets;
	
	
	
	Logger logger = LoggerFactory.getLogger(SetRepositoryImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	
	private SetRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void add(Set entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Set> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Set> getById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void update(Set entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Set entity) {
		// TODO Auto-generated method stub
		
	}
	
	
}
