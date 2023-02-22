package com.ecommerce.enkabutikiw.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Categorie {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String image;

    @OneToMany(mappedBy = "categorie")
    private List<Produits> Listproduits = new ArrayList<>();

}
