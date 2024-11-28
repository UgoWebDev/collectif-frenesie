package com.frenesie.collectif.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "artistes")
public class Artiste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String nom;

    @Column(length = 500)
    @Size(max = 500, message = "La biographie ne peut pas dépasser 500 caractères")
    private String biographie;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "artiste", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Set> sets = new ArrayList<>();

    @ManyToMany(mappedBy = "artistes")
    private List<Evenement> evenements = new ArrayList<>();
    
    public enum Genre {
        TECHNO, HOUSE, MINIMAL, ELECTRO, AUTRE
    }
    
    // Constructeur
    public Artiste(String nom, Genre genre) {
        this.nom = nom;
        this.genre = genre;
    }
}