package com.frenesie.collectif.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "evenements")
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de l'événement est requis")
    @Column(nullable = false)
    private String nom;

    private String description;

    @Future(message = "La date de début doit être dans le futur")
    @Column(nullable = false)
    private LocalDateTime dateDebut;

    @Column(nullable = false)
    private LocalDateTime dateFin;

    private String lieu;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToMany
    @JoinTable(
        name = "evenement_artiste",
        joinColumns = @JoinColumn(name = "evenement_id"),
        inverseJoinColumns = @JoinColumn(name = "artiste_id")
    )
    private List<Artiste> artistes;

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
    private List<Billet> billets;

    public enum Statut {
        PLANIFIE, EN_COURS, TERMINE, ANNULE
    }
}