package com.frenesie.collectif.service;

import com.frenesie.collectif.model.Evenement;
import com.frenesie.collectif.repository.EvenementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {

    private final EvenementRepository evenementRepository;

    // Injection de dépendance via le constructeur
    
    public EvenementService(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    // Récupérer tous les événements
    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    // Récupérer un événement par son ID
    public Optional<Evenement> getEvenementById(Long id) {
        return evenementRepository.findById(id);
    }

    // Créer ou mettre à jour un événement
    public Evenement saveEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    // Supprimer un événement par son ID
    public void deleteEvenement(Long id) {
        evenementRepository.deleteById(id);
    }
    
    // Ajouter d'autres méthodes de service si nécessaire
}
