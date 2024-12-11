package com.frenesie.collectif.model;


import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Valid
public enum Role {
    ADMIN,
    USER,
    ARTIST
}
