package com.frenesie.collectif.repository;

import com.frenesie.collectif.model.Artiste;
import com.frenesie.collectif.model.Artiste.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtisteRepository extends JpaRepository<Artiste, Long> {
    List<Artiste> findByGenre(Genre genre);
}
