package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.DTO.produit.ProduitResponse;
import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface ProduitService {
    MessageResponse ajoutProduit(Produits produit);
    MessageResponse supprimerProduit(Long id);

    ProduitResponse findById(Long id);

    List<ProduitResponse> findAll();

    ProduitResponse ModifierProduit(Produits produit, Long id);

    List<ProduitResponse> mapToProduitListe(List<Produits> produitsList);

    ProduitResponse mapToProduit(Produits produits);
}
