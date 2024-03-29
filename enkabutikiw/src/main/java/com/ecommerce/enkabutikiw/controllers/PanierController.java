package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.DTO.panier.PanierResponse;
import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.PanierRepository;
import com.ecommerce.enkabutikiw.repository.ProduitsRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.PanierService;
import com.ecommerce.enkabutikiw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/panier")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

public class PanierController {


    @Autowired
    private PanierService panierService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private ProduitsRepository produitsRepository;
    @Autowired
    private UserService userService;


    //   ICI ON AJOUTE UN PRODUIT AU PANIER
    @PostMapping("/ajouter/{produit}/{id}")
    public MessageResponse AjouterPanier(@RequestBody Panier panier, @PathVariable("produit") Produits produit, @PathVariable Long id) {


        User us = userRepository.findById(id).get();
            Long Qte =  (panier.getQuantite());
           // panier.setUser(user);
            panier.setQuantite(panier.getQuantite());
            //panier.setUser(us);

        panier.setTotalproduit((produit.getPrix()) * panier.getQuantite());
            panier.getProduits().add(produit);
     //   MessageResponse message = new MessageResponse("Impossible d'ajouté au panier");

            return  panierService.ajoutPanier(panier, produit);




    }

    @PostMapping("/ajouteraupanier/{produit}/{id}")
    public MessageResponse ajouterAuPanier(@PathVariable("produit") Produits produit, @PathVariable Long id) {


        User us = userRepository.findById(id).get();
        Panier mon_panier = new Panier();

        if (us.getPanier() == null) {
            mon_panier.setQuantite(1L);
            mon_panier.getProduits().add(produit);
            mon_panier.setTotalproduit(produit.getPrix());
            panierRepository.save(mon_panier);
            us.setPanier(mon_panier);
            userRepository.save(us);
        } else {
            mon_panier = us.getPanier();
            mon_panier.setQuantite(mon_panier.getQuantite()+1);
            mon_panier.getProduits().add(produit);
            mon_panier.setTotalproduit(mon_panier.getTotalproduit() + produit.getPrix());
            panierRepository.save(mon_panier);
        }


//        panier.setTotalproduit((produit.getPrix()) * panier.getQuantite());
//        panier.getProduits().add(produit);
        //   MessageResponse message = new MessageResponse("Impossible d'ajouté au panier");

        return  panierService.ajoutPanier(mon_panier, produit);




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

    public List<PanierResponse> mapToPanierResponseList( List<Panier> panierList){
        return panierService.mapToPanierList(panierList);
    }

//    @GetMapping("/ListeP/{id}")
//    public List<Object>[] liste(@PathVariable("id") Long id){
//        User us = userRepository.findById(id).get();
//        return panierRepository.listPanier(us.getId());
//    }
    @GetMapping("/PanierParUser/{id}")
    public List<Panier> UserParPanier(@PathVariable Long id){
        User us = userRepository.findById(id).get();
        return panierRepository.findByUser(us);
    }


}
