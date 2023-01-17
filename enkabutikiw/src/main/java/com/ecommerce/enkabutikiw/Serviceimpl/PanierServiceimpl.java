package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.PanierRepository;
import com.ecommerce.enkabutikiw.services.PanierService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PanierServiceimpl implements PanierService {


    @Autowired
    private final PanierRepository panierRepository;
    @Override
    public MessageResponse ajoutPanier(Panier panier, Produits produits) {
        Panier  panier2 = panierRepository.findByProduits(produits);
        Boolean panier3 = panierRepository.existsByProduits(produits);

        if(!panier3){
          //  panier.setProduits((List<Produits>) produits.getUser());
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produits.getPrix()));
            panierRepository.save(panier);
            MessageResponse message = new MessageResponse("Produit ajouté au panier");
            return message;
        }
        else {
            panier2.setTotalproduit(panier2.getTotalproduit() + (produits.getPrix()*panier.getQuantite()));
            panier2.setQuantite(panier2.getQuantite() + panier.getQuantite());
            panierRepository.save(panier2);
            MessageResponse message = new MessageResponse("Quantité modifiée avec succès");
            return message;
        }
    }


    @Override
    public MessageResponse supprimer(Panier panier, Produits produits) {
        Panier  panier1 = panierRepository.findByProduits(produits);
        Boolean panier2 = panierRepository.existsByProduits(produits);

        panierRepository.delete(panier1);
        MessageResponse message = new MessageResponse("Produit supprimer du panier");
        return message;    }

    @Override
    public MessageResponse validerpanier() {
        return null;
    }

    @Override
    public MessageResponse cleanPanier(Panier id) {
        panierRepository.deleteAll();
        MessageResponse message = new MessageResponse(" Tout le produit supprimer du panier");

        return message;
    }

    @Override
    public List<Panier> liste() {
        return panierRepository.findAll();
    }


}
