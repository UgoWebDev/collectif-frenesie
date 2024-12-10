package com.frenesie.collectif.repository;

import com.frenesie.collectif.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    boolean existsByName(String name);
    List<Artist> findByGenre(Artist genre);
    
}
