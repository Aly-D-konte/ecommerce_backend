package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface PanierService {


    MessageResponse ajoutPanier(Panier panier, Produits produits);
    MessageResponse supprimer(Panier panier, Produits produits);
    MessageResponse validerpanier();
    MessageResponse cleanPanier(Panier id);

    List<Panier> liste();

}
