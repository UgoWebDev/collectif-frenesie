package com.frenesie.collectif.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.frenesie.collectif.model.Artist;

@Service
public interface ArtistService {
    Artist createArtist(Artist artist);
    Artist saveArtist(Artist artist);
	Artist updateArtist(Integer id, Artist artistDetails);
	Artist getArtistById(Integer id);
	Artist findById(Integer id);
    void deleteById(Integer id);
	List<Artist> findArtistsByGenre(Artist genre);
	List<Artist> findAllArtists();
	Artist findArtistById(Integer id);

}

