package com.ecommerce.enkabutikiw.DTO.produit;

import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.models.Type_produit;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitResponse {
    private Long id;
    private String nom;
    private String description;
    private String marque;
    private Long prix;
    private String image;
    private String imageDas;
    private Long quantite_disponible;
    private Type_produit type_produit;
    private Categorie categorie;
    private Boutique boutique;
}
