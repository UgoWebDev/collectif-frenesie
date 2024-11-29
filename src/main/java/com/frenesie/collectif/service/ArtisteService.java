package com.frenesie.collectif.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.frenesie.collectif.exception.ResourceNotFoundException;
import com.frenesie.collectif.model.Artiste;
import com.frenesie.collectif.model.Artiste.Genre;
import com.frenesie.collectif.repository.ArtisteRepository;

@Service
public class ArtisteService {
	
    private final ArtisteRepository artisteRepository;


    public ArtisteService(ArtisteRepository artisteRepository) {
        this.artisteRepository = artisteRepository;
    }
    
    public List<Artiste> findAllArtistes() {
        return artisteRepository.findAll();
    }

    public Artiste ajouterArtiste(Artiste artiste) {
        if (artisteRepository.existsByNom(artiste.getNom())) {
            throw new RuntimeException("L'artiste existe déjà !");
        }
        return artisteRepository.save(artiste);
    }


    public List<Artiste> findArtistesByGenre(Genre genre) {
        List<Artiste> artistes = artisteRepository.findByGenre(genre);
        if (artistes.isEmpty()) {
            throw new ResourceNotFoundException("Aucun artiste trouvé pour le genre : " + genre);
        }
        return artistes;
    }
    
    
}
