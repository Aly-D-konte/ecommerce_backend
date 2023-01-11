package com.ecommerce.enkabutikiw.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produits {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private String marque;
    private Long prix;
    private String modele;
    private String capacite;
    private String image;
    private String type;

    //Mapping
    @ManyToOne
    private Categorie categorie;

    @ManyToOne
    private User user;

    @ManyToOne
    Boutique boutique;

}
