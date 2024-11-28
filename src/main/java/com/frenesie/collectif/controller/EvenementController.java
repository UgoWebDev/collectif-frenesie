package com.frenesie.collectif.controller;

import com.frenesie.collectif.model.Evenement;
import com.frenesie.collectif.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    private final EvenementService evenementService;

    
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    // Récupérer tous les événements
    @GetMapping
    public List<Evenement> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

    // Récupérer un événement par ID
    @GetMapping("/{id}")
    public ResponseEntity<Evenement> getEvenement(@PathVariable Long id) {
        Optional<Evenement> evenement = evenementService.getEvenementById(id);
        return evenement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer ou mettre à jour un événement
    @PostMapping
    public Evenement createEvenement(@RequestBody Evenement evenement) {
        return evenementService.saveEvenement(evenement);
    }

    // Supprimer un événement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvenement(@PathVariable Long id) {
        evenementService.deleteEvenement(id);
        return ResponseEntity.noContent().build();
    }
}
