package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.img.Projetimage;
import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.models.Type_produit;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.CategorieRepository;
import com.ecommerce.enkabutikiw.repository.TypeProduitRepository;
import com.ecommerce.enkabutikiw.services.CategorieService;
import com.ecommerce.enkabutikiw.services.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/typeproduit")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class TypeProduitController {


    @Autowired
    TypeProduitService typeProduitService;
    @Autowired
    private TypeProduitRepository typeProduitRepository;



    @PostMapping("/ajouter")
    // @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')   ")

    public MessageResponse ajoutTypeproduit(@RequestBody Type_produit type_produit
                                          ) throws IOException {
        type_produit.setNom(type_produit.getNom());

        if (typeProduitRepository.findByNom(type_produit.getNom()) == null){

            return typeProduitService.ajoutType(type_produit);

        }else {
            MessageResponse message = new MessageResponse("Type_produit existe déja");
            return message;
        }


    }



    @GetMapping("/liste")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN') ")

    public List<Type_produit> list(Type_produit type_produit){
        return typeProduitService.liste();
    }

    //@GetMapping("")



    @PutMapping("/modifier/{id}")

    public Type_produit modifierType(@RequestBody() Type_produit type_produit, @PathVariable Long id){
        return typeProduitService.ModifierType(type_produit, id);
    }


    @DeleteMapping("/supprimer/{id}")
    public MessageResponse supprimerType(@PathVariable Long id){
        return typeProduitService.supprimerType(id);
    }

}
