package com.frenesie.collectif.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frenesie.collectif.controller.FrenesieAdviceController.ResourceNotFoundException;
import com.frenesie.collectif.model.Artist;
import com.frenesie.collectif.repository.ArtistRepository;


@Service
public class ArtistServiceImpl implements ArtistService {

	@Autowired
    private ArtistRepository artistRepository;

    @Override
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }
    
    @Override
    public Artist updateArtist(Integer id, Artist artistDetails) {
    	Artist artist = getArtistById(id);
        artist.setName(artistDetails.getName());
        artist.setDescription(artistDetails.getDescription());
        artist.setGenre(artistDetails.getGenre());
        artist.setImageUrls(artistDetails.getImageUrls());
        return artistRepository.save(artist);
    }
    
    @Override
    public Artist findArtistById(Integer id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found with id: " + id));
    }

    @Override
    public void deleteById(Integer id) {
        artistRepository.deleteById(id);
    }
    
    @Override
    public Artist getArtistById(Integer id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
    }

    @Override
    public List<Artist> findArtistsByGenre(Artist genre) {
        List<Artist> artists = artistRepository.findByGenre(genre);
        if (artists.isEmpty()) {
            throw new ResourceNotFoundException("Aucun artist trouvé pour le genre : " + genre);
        }
        return artists;
    }

    @Override
	public List<Artist> findAllArtists() {
	    List<Artist> artists = artistRepository.findAll();
	    if (artists.isEmpty()) {
	        System.out.println("Aucun artiste trouvé");
	    }
	    return artists;	
	}

    @Override
    public Artist findById(Integer id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found with id: " + id));
    }

	
}
