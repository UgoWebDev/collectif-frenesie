package com.frenesie.collectif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frenesie.collectif.exception.ResourceNotFoundException;
import com.frenesie.collectif.model.Artist;
import com.frenesie.collectif.repository.ArtistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistService {
	
    private final ArtistRepository artistRepository;


    public Artist saveArtist(Artist artist) {
        if (artistRepository.existsByName(artist.getName())) {
            throw new RuntimeException("L'artist existe déjà !");
        }
        return artistRepository.save(artist);
    }

    public Optional<Artist> getArtistById(Integer id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isEmpty()) {
            throw new ResourceNotFoundException("Aucun artist trouvé");
        }
        return artistRepository.findById(id);
    }


    public List<Artist> findArtistsByGenre(Artist genre) {
        List<Artist> artists = artistRepository.findByGenre(genre);
        if (artists.isEmpty()) {
            throw new ResourceNotFoundException("Aucun artist trouvé pour le genre : " + genre);
        }
        return artists;
    }

	public List<Artist> findAllArtists() {
		// TODO Auto-generated method stub
		return null;
	}

    public List<Artist> getAllArtists() {
        return artistRepository.findAll(); // Récupère tous les artistes de la base de données
    }
    
}
