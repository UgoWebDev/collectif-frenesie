package com.frenesie.collectif.service;

import com.frenesie.collectif.model.Evenement;
import com.frenesie.collectif.repository.EvenementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementServiceImpl implements EvenementService {

    private final EvenementRepository evenementRepository;

    public EvenementServiceImpl(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    @Override
    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    @Override
    public Optional<Evenement> getEvenementById(Long id) {
        return evenementRepository.findById(id);
    }

    @Override
    public Evenement saveEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @Override
    public void deleteEvenement(Long id) {
        evenementRepository.deleteById(id);
    }
}
