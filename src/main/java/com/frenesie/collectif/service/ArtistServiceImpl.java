package com.frenesie.collectif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frenesie.collectif.model.Artist;
import com.frenesie.collectif.repository.ArtistRepository;


@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    
    public ArtistServiceImpl(ArtistRepository artistRepository) {
    	this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAll() {
        return artistRepository.getAll();
    }

    @Override
    public Optional<Artist> getById(int id) {
        return artistRepository.getById(id);
    }

    @Override
    public void add(Artist artist) {
        artistRepository.add(artist);
    }

    @Override
    public void update(Artist artist) {
    	Optional<Artist> artistOpt = getById(artist.getId());
    	if ( artistOpt.isPresent()) {
            artistRepository.update(artist);
    	} else{
    		throw new RuntimeException();
    	}
    }

    @Override
    public void delete(int id) {
        artistRepository.delete(id);
    }
    
    @Override
    public void save(Artist artist) {
    	if (artist.getId()==null) {
    		this.add(artist);
    		return;
    	}
    	this.update(artist);
    }

}
