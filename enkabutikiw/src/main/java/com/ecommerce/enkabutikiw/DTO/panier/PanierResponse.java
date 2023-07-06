package com.ecommerce.enkabutikiw.DTO.panier;

import com.ecommerce.enkabutikiw.models.Produits;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PanierResponse {
    private Long id;
    private Long Totalproduit;
    private Long quantite;
    private List<Produits> produits;
}
