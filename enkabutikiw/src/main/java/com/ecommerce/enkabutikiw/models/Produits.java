package com.ecommerce.enkabutikiw.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Long quantite_disponible;
    private String type;

    //Mapping
    @ManyToOne
    private Categorie categorie;

    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "produits_boutique",
            joinColumns = @JoinColumn(name = "produits_id"),
            inverseJoinColumns = @JoinColumn(name = "boutique_id"))
    private Set<Boutique> boutiques = new HashSet<>();


}
