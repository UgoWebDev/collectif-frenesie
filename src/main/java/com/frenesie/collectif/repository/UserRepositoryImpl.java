package com.frenesie.collectif.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	
	public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public Optional<User> findUserByEmail(String email) {
		String sql = "SELECT id, username, password, email, role where email=?";
		User user = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),email);
		return Optional.ofNullable(user);
	}
	
}
