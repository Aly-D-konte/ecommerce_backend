package com.ecommerce.enkabutikiw.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String image;
    private String imageDas;

    private Long quantite_disponible;


    //@JsonIgnore
    @ManyToOne
    private Type_produit type_produit;

    //Mapping
   // @JsonIgnore
    @ManyToOne
    private Categorie categorie;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "produits_boutique",
            joinColumns = @JoinColumn(name = "produits_id"),
            inverseJoinColumns = @JoinColumn(name = "boutique_id"))
    private Set<Boutique> boutiques = new HashSet<>();


}
