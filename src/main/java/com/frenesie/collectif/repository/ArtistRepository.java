package com.frenesie.collectif.repository;

import java.util.List;
import java.util.Optional;

import com.frenesie.collectif.model.Artist;


public interface ArtistRepository {
    void add(Artist artist);
    List<Artist> getAll();
    Optional<Artist> getById(int id);
    void update(Artist artist);
    void delete(int id);
}
