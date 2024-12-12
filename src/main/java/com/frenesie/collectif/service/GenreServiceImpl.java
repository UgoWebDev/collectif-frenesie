package com.frenesie.collectif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frenesie.collectif.model.Genre;
import com.frenesie.collectif.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {
	private GenreRepository genreRepository;

	@Override
	public void add(Genre entity) {
		genreRepository.add(entity);
	}

	@Override
	public List<Genre> getAll() {
		return genreRepository.getAll();
	}

	@Override
	public Optional<Genre> getById(int id) {
		return genreRepository.getById(id);
	}

	@Override
	public void update(Genre entity) {
		genreRepository.update(entity);
	}

	@Override
	public void save(Genre entity) {
		// TODO Auto-generated method stub
		throw new RuntimeException("save non supporté pour les Genres");
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		throw new RuntimeException("delete non supporté pour les Genres");
	}
	
}
