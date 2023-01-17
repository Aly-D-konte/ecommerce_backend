package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.PanierRepository;
import com.ecommerce.enkabutikiw.repository.ProduitsRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/panier")
public class PanierController {


    @Autowired
    private PanierService panierService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private ProduitsRepository produitsRepository;


    //   ICI ON AJOUTE UN PRODUIT AU PANIER
    @PostMapping("/ajouter/{produit}")
    public MessageResponse AjouterPanier(@RequestBody Panier panier, @PathVariable("produit") Produits produit) {


            Long Qte =  (panier.getQuantite());
           // panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()) * panier.getQuantite());
            panier.getProduits().add(produit);
     //   MessageResponse message = new MessageResponse("Impossible d'ajouté au panier");

            return  panierService.ajoutPanier(panier, produit);




    }


    //   ICI ON AJOUTE UN PRODUIT AU PANIER EN PRECISANT LE NOMBRE DE PRODUITS

    @PutMapping("/modifierquantite/{produit}/{user}")
    public MessageResponse UpdateQuantite(@RequestBody Panier panier, @PathVariable("produit") Produits produit, @PathVariable("user") User user) {
        if(userRepository.findById(user.getId()) != null) {
            Long Qte =  (panier.getQuantite());
            //panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            panier.setTotalproduit((produit.getPrix()));
            panier.getProduits().add(produit);
            return  panierService.ajoutPanier(panier,produit);
        }else {
            MessageResponse message = new MessageResponse("Impossible de mettre en jour votre panier");
            return message;
        }
    }

    //Suppression d'un produit dans le panier
    @DeleteMapping("/supprimer/{panier}/{produit}/{user}")
    public MessageResponse Supprimer(@PathVariable("panier") Panier panier, @PathVariable("produit") Produits produit, @PathVariable("user") User user) {

        if(panierRepository.findById(panier.getId()) != null && userRepository.findById(user.getId()) != null && produitsRepository.findById(produit.getId()) != null ) {

            return  panierService.supprimer(panier,produit);
        }else {
            MessageResponse message = new MessageResponse("Impossible de supprimer");
            return message;
        }
    }

    @DeleteMapping("/clean/{panier}")
    public MessageResponse clean(Panier id){
        return panierService.cleanPanier(id);
    }

    @GetMapping("/liste")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN') ")

    public List<Panier> list(){
        return panierService.liste();
    }
}
