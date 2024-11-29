package com.frenesie.collectif.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.frenesie.collectif.controller.dto.RegistrationDto;
import com.frenesie.collectif.model.Utilisateur;
import com.frenesie.collectif.repository.UtilisateurRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilisateurService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return new org.springframework.security.core.userdetails.User(
            utilisateur.getEmail(),
            utilisateur.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(utilisateur.getRole().name()))
        );
    }

    public Utilisateur register(RegistrationDto dto) {
        if (utilisateurRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Un compte existe déjà avec cet email");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setUsername(dto.getNom());
        utilisateur.setPassword(passwordEncoder.encode(dto.getMotDePasse()));
        utilisateur.setRole(Utilisateur.Role.UTILISATEUR);

        return utilisateurRepository.save(utilisateur);
    }
}
