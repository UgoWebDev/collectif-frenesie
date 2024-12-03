package com.frenesie.collectif.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "events")
@Valid
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Le nom de l'événement est requis")
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Future(message = "La date de début doit être dans le futur")
    private LocalDateTime timeSet;

    @Column(nullable = false)
    private LocalDateTime timeEnd;

    @Column(nullable = false)
    private String place;

    @Enumerated(EnumType.STRING)
    private Statut status;

    @ManyToMany
    @JoinTable(
        name = "event_artist",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Billet> billets;

    public enum Statut {
        PLANNED, RUNNING, ENDED, CANCELED
    }
}
