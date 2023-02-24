package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.models.Type_produit;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface TypeProduitService {
    MessageResponse ajoutType(Type_produit type_produit);
    MessageResponse supprimerType(Long id);
    Type_produit ModifierType(Type_produit type_produit, Long id);
    List<Type_produit> liste();
}
