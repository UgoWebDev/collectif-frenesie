package com.frenesie.collectif.service;

import com.frenesie.collectif.model.Evenement;

import java.util.List;
import java.util.Optional;

public interface EvenementService {
    List<Evenement> getAllEvenements();
    Optional<Evenement> getEvenementById(Long id);
    Evenement saveEvenement(Evenement evenement);
    void deleteEvenement(Long id);
}
