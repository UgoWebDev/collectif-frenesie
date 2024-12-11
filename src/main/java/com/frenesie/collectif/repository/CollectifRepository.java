package com.frenesie.collectif.repository;

import java.util.Optional;


import com.frenesie.collectif.model.Collectif;

public interface CollectifRepository {
    Optional<Collectif> findByNom(String nom);

}
