package com.frenesie.collectif.repository;

import com.frenesie.collectif.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    
    // Méthode personnalisée pour vérifier l'existence d'un artiste par son nom
    boolean existsByName(String name);
   
    
    // Méthode pour trouver les artistes par genre
    List<Artist> findByGenre(Artist genre);


    
}
