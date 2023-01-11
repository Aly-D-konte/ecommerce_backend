package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.img.SaveImage;
import com.ecommerce.enkabutikiw.models.*;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.ProduitsRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.ProduitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/produit")
@AllArgsConstructor

public class ProduitController {

    @Autowired
    private final ProduitService produitService;

    @Autowired
    private final ProduitsRepository produitsRepository;
    @Autowired
    private final UserRepository userRepository;
    @PostMapping("/ajouter")
    @PreAuthorize("hasRole('SUPER_ADMIN')")

    public MessageResponse ajoutProduit(@Param("nom") String nom,
                                        @Param("description") String description,
                                        @Param("marque") String marque,
                                        @Param("modele") String modele,
                                        @Param("prix") Long prix,
                                        @Param("capacite") String capacite,
                                        @Param("type") String type,
                                        @Param("user_id") User user_id,
                                        @Param("image") String image,
                                        @Param("categorie_id") Categorie categorie_id,
                                        @Param("commande_id") Commande commande_id,
                                        @Param("panier_id") Panier panier_id,
                                        @Param("boutique_id") Boutique boutique_id,
                                        @Param("file") MultipartFile file) throws IOException {
        Produits produits = new Produits();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        produits.setNom(nom);
        produits.setDescription(description);
        produits.setModele(modele);
        produits.setMarque(marque);
        produits.setCapacite(capacite);
        produits.setPrix(prix);
        produits.setImage(nomfile);
        produits.setType(type);
        produits.setUser(user_id);
        produits.setBoutique(boutique_id);
        produits.setCategorie(categorie_id);
        produits.setUser(userRepository.findById(1L).get());

        if (produitsRepository.findByNom(nom) == null){

//            String uploaDir = "C:\\Users\\adkonte\\Documents\\ecommerce_backend\\enkabutikiw\\src\\test\\Images";
//           ConfigImage.saveimg(uploaDir, nomfile, file);
            produits.setImage(SaveImage.save(file,produits.getImage()));
            return produitService.ajoutProduit(produits);

        }else {
            MessageResponse message = new MessageResponse("Produit existe déja");
            return message;
        }


    }

    @GetMapping("/liste")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN') ")

    public List<Produits> list(){
        return produitService.liste();
    }

    @PutMapping("/modifier/{id}")

    @PreAuthorize("hasRole('SUPER_ADMIN')")

    public Produits modifierProduit(@Param("boutique") Produits produits, @PathVariable Long id){
        return produitService.ModifierProduit(produits, id);
    }




    @DeleteMapping("/supprimer/{id}")
    //@PreAuthorize("hasRole('SUPER_ADMIN')")

    public MessageResponse supprimerProduits(@PathVariable("id") Long id){
        return produitService.supprimerProduit(id);
    }
}
