package com.frenesie.collectif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frenesie.collectif.exception.ArtistNotFoundException;
import com.frenesie.collectif.model.Artist;
import com.frenesie.collectif.repository.ArtistRepository;


@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    
    public ArtistServiceImpl(ArtistRepository artistRepository) {
    	this.artistRepository = artistRepository;
    }
    


}
