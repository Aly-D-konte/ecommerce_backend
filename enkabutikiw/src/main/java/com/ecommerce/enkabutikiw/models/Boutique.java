package com.prodevma.immodev.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Boutique {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private String adresse;
    private String image;
    private boolean etat = false;
    @ManyToOne
    private User user;

}
