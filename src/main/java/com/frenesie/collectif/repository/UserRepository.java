package com.frenesie.collectif.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    
    boolean existsByEmail(String email); // Vérifie si un email existe déjà

    Optional<User> findByEmail(String email); // Trouve un utilisateur par email

}