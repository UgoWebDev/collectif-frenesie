package com.frenesie.collectif.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "billets")
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private BigDecimal prix;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User utilisateur;

    public enum Statut {
        DISPONIBLE, RESERVE, VENDU, UTILISE
    }
}