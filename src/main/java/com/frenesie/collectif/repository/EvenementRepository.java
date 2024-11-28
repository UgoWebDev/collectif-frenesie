package com.frenesie.collectif.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frenesie.collectif.model.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    List<Evenement> findByDateDebutBetween(Date start, Date end);
    List<Evenement> findByLieu(String lieu);
}