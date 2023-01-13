package com.ecommerce.enkabutikiw.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private Long montant;
    private Date date;
    private Long quantite;

    @ManyToOne
    private User user;

    @ManyToOne
    private  Paiment paiment;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Panier> paniers ;

}
