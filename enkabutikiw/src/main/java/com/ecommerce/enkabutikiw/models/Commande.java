package com.ecommerce.enkabutikiw.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Commande {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    private Date date;


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Statut statut;


//    public Commande(String code, Long montant, Date date, Long quantite) {
//        this.code = code;
//        this.montant = montant;
//        this.date = date;
//        this.quantite = quantite;
//
//    }
    @ManyToOne
    private User user;


    @OneToOne
    private Panier panier;



//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Panier paniers ;


}
