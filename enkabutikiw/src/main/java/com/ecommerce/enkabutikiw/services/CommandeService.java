package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.Commande;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface CommandeService {

    MessageResponse ajouteCommande(Commande commande);
    MessageResponse supprimerCommande(Long id);
    Commande ModifierCommande(Commande commande, Long id);
    List<Commande> liste();
}
