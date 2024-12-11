package com.frenesie.collectif.service;

import org.springframework.stereotype.Service;

import com.frenesie.collectif.model.Collectif;
import com.frenesie.collectif.repository.CollectifRepository;

@Service
public class CollectifService {
    private final CollectifRepository collectifRepository;

    public CollectifService(CollectifRepository collectifRepository) {
        this.collectifRepository = collectifRepository;
    }

}
