package com.frenesie.collectif.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Billet {

    private Long id;

    private String reference;
    
    private BigDecimal prix;

    private Statut statut;

    private Event event;


    private User utilisateur;

    public enum Statut {
        DISPONIBLE, RESERVE, VENDU, UTILISE
    }
}