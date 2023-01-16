package com.ecommerce.enkabutikiw.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDto {
    private String code;
    private Long montant;
    private Date date;
    private Long quantite;
    private String statut;
    private   String nom;

    @Override
    public String toString() {
        return "commandeDto{" +
                "code='" + code + '\'' +
                ", montant=" + montant +
                ", date='" + date + '\'' +
                ", quantite='" + quantite + '\'' +
                ", statut='" + statut + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
