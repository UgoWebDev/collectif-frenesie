package com.frenesie.collectif.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frenesie.collectif.model.Collectif;

public interface CollectifRepository extends JpaRepository<Collectif, Long> {
    Optional<Collectif> findByNom(String nom);
}
