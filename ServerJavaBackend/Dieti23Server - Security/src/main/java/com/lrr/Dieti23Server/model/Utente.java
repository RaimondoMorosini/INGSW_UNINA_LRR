package com.lrr.Dieti23Server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "siti", columnDefinition = "json")
    private String siti;

    @Column(name = "area_geografica")
    private String areaGeografica;

    @Column(name = "bio", length = 256)
    private String bio;
}
