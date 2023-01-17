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
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")

    public MessageResponse ajoutProduit(@Param("nom") String nom,
                                        @Param("description") String description,
                                        @Param("marque") String marque,
                                        @Param("modele") String modele,
                                        @Param("prix") Long prix,
                                        @Param("quantite") Long quantite,
                                        @Param("capacite") String capacite,
                                        @Param("type") String type,
                                        @Param("categorie_id") Categorie categorie_id,

                                        @Param("boutique_id") Boutique boutique_id,
                                        @Param("file") MultipartFile file) throws IOException {
        Produits produit = new Produits();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setModele(modele);
        produit.setMarque(marque);
        produit.setCapacite(capacite);
        produit.setQuantite(quantite);
        produit.setPrix(prix);
   //     produits.setImage(nomfile);
        produit.setType(type);
        produit.setCategorie(categorie_id);
       // produit.setUser(userRepository.findById(1L).get());

        if (produitsRepository.findByNom(nom) == null){

//            String uploaDir = "C:\\Users\\adkonte\\Documents\\ecommerce_backend\\enkabutikiw\\src\\test\\Images";
//           ConfigImage.saveimg(uploaDir, nomfile, file);
            produit.setImage(SaveImage.save(file,file.getOriginalFilename()));
            return produitService.ajoutProduit(produit,boutique_id);

        }else {
            MessageResponse message = new MessageResponse("Produit existe déja");
            return message;
        }


    }

    @GetMapping("/liste")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN') ")

    public List<Produits> list(){
        return produitService.liste();
    }

    @GetMapping("/afficher/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN') ")

    public Produits getProductById(@PathVariable("id") Long id) {
        return produitService.findById(id);
    }


    @PutMapping("/modifier/{id}")

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('SUPER_ADMIN') ")

    public Produits modifierProduit(@Param("boutique") Produits produits, @PathVariable Long id){
        return produitService.ModifierProduit(produits, id);
    }




    @DeleteMapping("/supprimer/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")

    public MessageResponse supprimerProduits(@PathVariable("id") Long id){
        return produitService.supprimerProduit(id);
    }
}
