package com.frenesie.collectif.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.Genre;

@Repository
public class GenreRepositoryJdbcImpl implements GenreRepository {
	private List<Genre> genres;
	
	public GenreRepositoryJdbcImpl() {
		genres = new ArrayList<Genre>();
		genres.add(new Genre(1, "Hard Techno"));
		genres.add(new Genre(2, "Acid"));
		genres.add(new Genre(3, "Trance"));
		genres.add(new Genre(4, "House"));
	}
	
	@Override
	public void add(Genre entity) {
		genres.add(entity);
		
	}

	@Override
	public List<Genre> getAll() {
		return genres.stream().collect(Collectors.toList());
	}

	@Override
	public Optional<Genre> getById(int id) {
		return genres.stream().filter(genre->genre.getId()==id).findFirst();
	}
	

	@Override
	public void update(Genre entity) {
		throw new RuntimeException("update non supporté pour les Genres");
		
	}

	@Override
	public void delete(int id) {
		throw new RuntimeException("delete non supporté pour les Genres");
		
	}
}
