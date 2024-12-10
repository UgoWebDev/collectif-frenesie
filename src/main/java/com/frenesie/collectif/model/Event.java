package com.frenesie.collectif.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    private String title;
    
    @Column(nullable = false)
    @NotBlank(message = "Le Lieu de l'événement est requis")
    private String location;
    
    @Column(nullable = false, length = 100)
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;
    
    @ElementCollection
    private List<String> imageUrls;

    @Column(nullable = false)
    @Future(message = "La date de début doit être dans le futur")
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;


    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(
        name = "event_artist",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Billet> billets;

    public enum Status {
        PLANNED, RUNNING, ENDED, CANCELED
    }
    
    @AssertTrue(message = "La date de fin doit être après la date de début")
    public boolean isValidTimeRange() {
        return startTime == null || endTime == null || endTime.isAfter(startTime);
    }
}
