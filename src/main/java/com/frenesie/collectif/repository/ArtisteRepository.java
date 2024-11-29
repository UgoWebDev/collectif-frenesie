package com.frenesie.collectif.repository;

import com.frenesie.collectif.model.Artiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtisteRepository extends JpaRepository<Artiste, Long> {
    
    // Méthode personnalisée pour vérifier l'existence d'un artiste par son nom
    boolean existsByNom(String nom);
    
    // Méthode pour trouver les artistes par genre
    List<Artiste> findByGenre(Artiste.Genre genre);
}
