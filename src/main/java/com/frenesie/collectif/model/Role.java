package com.frenesie.collectif.model;


import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "roles")
@Valid
public enum Role {
    ADMIN,
    USER,
    ARTIST
}
