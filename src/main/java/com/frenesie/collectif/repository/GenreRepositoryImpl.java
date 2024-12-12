package com.frenesie.collectif.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.Genre;

@Repository
@Primary
public class GenreRepositoryImpl implements GenreRepository {
	Logger logger = LoggerFactory.getLogger(GenreRepositoryImpl.class);
	
    private JdbcTemplate jdbcTemplate;
    
    class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre();
            genre.setId(rs.getInt("id"));
            genre.setGenre(rs.getString("genre"));
            return genre;
        }
    }

	@Override
	public void add(Genre entity) {
		throw new RuntimeException("Not implemented !!!");		
	}

	@Override
	public List<Genre> getAll() {
		String sql = "SELECT * FROM genres";
        List<Genre> genres = jdbcTemplate.query(sql, new  GenreRowMapper());
        return genres;		    
	}

	@Override
	public Optional<Genre> getById(int id) {
		String sql = "SELECT id, genre FROM genres WHERE id=?";
		Genre genre = null;
		try {
			genre = jdbcTemplate.queryForObject(sql, new GenreRowMapper(), id);
		} catch (IncorrectResultSizeDataAccessException exc) {
			exc.printStackTrace();
			logger.warn("Le genre " + id + "n'a pas été trouvé.");
		}
		return Optional.ofNullable(genre);
	}

	@Override
	public void update(Genre entity) {
		throw new RuntimeException("Not implemented !!!");
		
	}

	@Override
	public void delete(int id) {
		throw new RuntimeException("Not implemented !!!");
		
	}
    
    
}
