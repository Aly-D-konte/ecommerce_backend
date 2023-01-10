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
public class Panier {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nombreproduit;

    @ManyToMany
    private List<Produits> produitsList = new ArrayList<>();


}
