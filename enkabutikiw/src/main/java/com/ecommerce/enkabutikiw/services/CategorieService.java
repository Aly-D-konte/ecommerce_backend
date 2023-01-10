package com.ecommerce.enkabutikiw.services;


import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface CategorieService {

    MessageResponse ajoutCategorie(Categorie categorie);
    MessageResponse supprimerCategorie(Long id);
    Categorie ModifierCategorie(Categorie categorie, Long id);
    List<Categorie> liste();
}
