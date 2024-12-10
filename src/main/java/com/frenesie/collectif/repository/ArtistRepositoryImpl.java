package com.frenesie.collectif.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.Artist;



@Repository
public abstract class ArtistRepositoryImpl implements ArtistRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	public ArtistRepositoryImpl (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Artist> findByGenre(Artist genre) {
		String sql = "select id, name, genre from artiste";
		
		List<Artist> artists = jdbcTemplate.query(sql, new ArtisteRowMapper());
		return artists;
	}
	
	class ArtisteRowMapper implements RowMapper<Artist>
	{
		@Override
		public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
			Artist	artist = new Artist();
			artist.setId((int) rs.getLong("id"));
			artist.setName(rs.getString("name"));
			artist.setGenre(rs.getString("genre"));

			return null;
		}
	}
	
}
