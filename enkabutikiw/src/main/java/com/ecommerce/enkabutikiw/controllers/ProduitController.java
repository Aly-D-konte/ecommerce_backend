package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.img.Projetimage;
import com.ecommerce.enkabutikiw.img.SaveImage;
import com.ecommerce.enkabutikiw.models.*;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.*;
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
@RequestMapping("/api/produit")

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@AllArgsConstructor

public class ProduitController {

    @Autowired
    private final ProduitService produitService;

    @Autowired
    private  final  TypeProduitRepository typeProduitRepository;

    @Autowired
    private final ProduitsRepository produitsRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private BoutiqueRepository boutiqueRepository;
    @Autowired
    private CategorieRepository categorieRepository;


    @PostMapping("/ajouter")
  //  @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")

    public MessageResponse ajoutProduit(@Param("nom") String nom,
                                        @Param("description") String description,
                                        @Param("marque") String marque,
                                        @Param("prix") Long prix,
                                        @Param("quantite_disponible") Long quantite_disponible,
                                        @Param("type_produit") Long type_produit,
                                        @Param("categorie_id") Long categorie_id,
                                        @Param("user_id") Long user_id,
                                        @Param("boutique_id") Long boutique_id,
                                        @Param("file") MultipartFile file) throws IOException {
        Produits produit = new Produits();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setMarque(marque);
        produit.setQuantite_disponible(quantite_disponible);
        produit.setPrix(prix);
        produit.getBoutiques().add(boutiqueRepository.findById(boutique_id).get());
        produit.setType_produit(typeProduitRepository.findById(type_produit).get());
        produit.setCategorie(categorieRepository.findById(categorie_id).get());

        //produit.setBoutiques(produit.getBoutiques());
        //     produits.setImage(nomfile);
        //produit.setCategorie(categorie_id);
        // produit.setUser(userRepository.findById(1L).get());

        produit.setUser(userRepository.findById(user_id).get());
        if (produitsRepository.findByNom(nom) == null){

//            String uploaDir = "C:\\Users\\adkonte\\Documents\\ecommerce_backend\\enkabutikiw\\src\\test\\Images";
//           ConfigImage.saveimg(uploaDir, nomfile, file);
            produit.setImage(Projetimage.save(file,file.getOriginalFilename()));
            return produitService.ajoutProduit(produit);

        }else {
            MessageResponse message = new MessageResponse("Produit existe d??ja");
            return message;
        }


    }

    @GetMapping("/liste")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN')    ")

    public List<Produits> list(){
        return produitService.liste();
    }

    @GetMapping("/afficher/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN') ")

    public Produits getProductById(@PathVariable("id") Long id) {
        return produitService.findById(id);
    }


    @PutMapping("/modifier/{id}")

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') ")

    public Produits modifierProduit(@Param("boutique") Produits produits, @PathVariable Long id){
        return produitService.ModifierProduit(produits, id);
    }




    @DeleteMapping("/supprimer/{id}")
    //@PreAuthorize("hasRole('SUPER_ADMIN')")

    public MessageResponse supprimerProduits(@PathVariable("id") Long id){
        return produitService.supprimerProduit(id);
    }


    @GetMapping("ProduitParUser/{id}")
   // @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') ")

    public List<Produits> produitByUser(@PathVariable("id") User id){
        return produitsRepository.findByUser(id);

    }
}
