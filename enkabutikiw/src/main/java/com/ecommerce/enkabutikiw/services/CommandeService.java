package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.Commande;
import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface CommandeService {


    MessageResponse ajouteCommande(Commande commande, Long id);
    MessageResponse supprimerCommande(Long id);
    Commande ModifierCommande(Commande commande, Long id);
    List<Commande> liste();
   // float getPanier(List<Panier> paniers);

    Commande findByCode(String code);
    float save(Produits id, List<Panier> paniers);
    MessageResponse commander(Commande commande,List<Panier> paniers, Long produits_id);
}
