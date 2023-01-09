package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface ProduitService {
    MessageResponse ajoutProduit(Produits produit);
    MessageResponse supprimerProduit(Long id);
    MessageResponse ModifierProduit(Produits produit, Long id);
    List<Produits> liste();
}
