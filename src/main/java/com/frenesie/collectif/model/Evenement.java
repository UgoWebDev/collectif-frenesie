package com.frenesie.collectif.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "evenements")
@Valid
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Le nom de l'événement est requis")
    private String nom;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Future(message = "La date de début doit être dans le futur")
    private LocalDateTime dateDebut;

	@Column(nullable = false)
    private LocalDateTime dateFin;
    
    //Verifier que dateFin est après dateDebut

    @Column(nullable = false)
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
    //Gérer les billets lors de la suppression d'événements ou d'artistes

    public enum Statut {
        PLANIFIE, EN_COURS, TERMINE, ANNULE
    }
    
    public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}
	
    public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}
    
}