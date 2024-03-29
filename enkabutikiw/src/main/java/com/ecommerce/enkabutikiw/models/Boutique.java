package com.ecommerce.enkabutikiw.models;

import com.ecommerce.enkabutikiw.models.User;
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
public class Boutique {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private String adresse;
    private String image;
    private String imageDas;
    private boolean etat = false;
    @ManyToOne
    private User user;

}
