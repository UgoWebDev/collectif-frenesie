package com.frenesie.collectif.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.Evenement;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}