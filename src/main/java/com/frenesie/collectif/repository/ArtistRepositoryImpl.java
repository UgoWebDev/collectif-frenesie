package com.frenesie.collectif.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.Artist;

@Repository
public class ArtistRepositoryImpl implements ArtistRepository {

	@Override
	public void add(Artist artist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Artist> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Artist> getById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void update(Artist artist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	
}
