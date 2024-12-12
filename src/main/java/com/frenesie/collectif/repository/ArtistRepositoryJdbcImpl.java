package com.frenesie.collectif.repository;

import com.frenesie.collectif.model.Artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class ArtistRepositoryJdbcImpl implements ArtistRepository{
	Logger logger = LoggerFactory.getLogger(ArtistRepositoryJdbcImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final JdbcTemplate jdbcTemplate;
	
	public ArtistRepositoryJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

    @Override
    public void add(Artist newArtist) {
    	
        String sql = "INSERT INTO artists (name, biography, genre)" + "VALUES (:name, :biography, :genre)";
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newArtist));
    }
    
    @Override
    public List<Artist> getAll() {
        String sql = "SELECT * FROM artists";
        List<Artist> artists = namedParameterJdbcTemplate.query(sql, 
        		new BeanPropertyRowMapper<>(Artist.class));
        return artists;
    }

    @Override
    public Optional<Artist> getById(int id) {
        String sql = "SELECT * FROM artists WHERE id = ?";
        Artist artist = null;
        try {
            artist = jdbcTemplate.queryForObject(sql,
            		new BeanPropertyRowMapper<>(Artist.class), id);
        } catch (DataAccessException exc) {
			logger.warn(exc.getMessage());
        }
		return Optional.ofNullable(artist);
    }

    @Override
    public void update(Artist artist) {
        String sql = "UPDATE artists SET name=:name, biography=:biography, genre=:genre WHERE id=:id";
        int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(artist));
        if (nbRows !=1) {
        	throw new RuntimeException("Le profil n'a pas pu être mis à jour");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM artists WHERE id = ?";
        int nbRows = jdbcTemplate.update(sql, id);
        if(nbRows !=1) {
        	throw new RuntimeException("Le profil n'a pas pu être supprimé");
        }
    }
}