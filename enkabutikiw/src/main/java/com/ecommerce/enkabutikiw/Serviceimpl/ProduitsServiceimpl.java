package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.DTO.boutique.BoutiqueResponse;
import com.ecommerce.enkabutikiw.DTO.produit.ProduitResponse;
import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.BoutiqueRepository;
import com.ecommerce.enkabutikiw.repository.ProduitsRepository;
import com.ecommerce.enkabutikiw.services.ProduitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProduitsServiceimpl implements ProduitService {

    @Autowired
    private final ProduitsRepository produitsRepository;
    private final BoutiqueRepository boutiqueRepository;

    @Override
    public MessageResponse ajoutProduit(Produits produit) {

        if (produitsRepository.findByNom(produit.getNom())== null){
        //    boutiqueRepository.save(produit.getBoutique());
            produitsRepository.save(produit);
            MessageResponse message = new MessageResponse("Produit ajoutée avec succès");
            return message;

        }else {
            MessageResponse message = new MessageResponse("Cet produit existe déjà");

            return message;
        }
    }

    @Override
    public MessageResponse supprimerProduit(Long id) {

        if (produitsRepository.findById(id) !=null){
            produitsRepository.deleteById(id);
            MessageResponse message = new MessageResponse("Produit supprimé avec succès");
            return message;

        }else {
            MessageResponse message = new MessageResponse("Produit non trouvé");

            return message;
        }    }


    @Override
    public Produits findById(Long id) {
        return produitsRepository.findById(id).orElse(null);
    }

    @Override
    public ProduitResponse ModifierProduit(Produits produit, Long id) {


        Produits modifierProduit = produitsRepository.findById(id).get();
        modifierProduit.setNom(produit.getNom());
        modifierProduit.setDescription(produit.getDescription());
        modifierProduit.setCategorie(produit.getCategorie());
       // modifierProduit.setType(produit.getType());
        modifierProduit.setImage(produit.getImage());

        modifierProduit.setMarque(produit.getMarque());

//        modifierProduit.setUser(produit.getUser());
        modifierProduit.setPrix(produit.getPrix());
        return this.mapToProduit(modifierProduit);
    }



    @Override
    public List<ProduitResponse> mapToProduitListe() {
        return null;
    }

    public List<ProduitResponse> mapToProduitListe( List<Produits> produitsList) {

        List<ProduitResponse> ProduitResponseList = new ArrayList<>();
        for (Produits produits: produitsList){
            ProduitResponseList.add(this.mapToProduit(produits));
        }
        return ProduitResponseList;
    }

//    @Override
//    public List<Produits> liste() {
//        return produitsRepository.findAll();
//    }

    @Override
    public ProduitResponse mapToProduit(Produits produits) {

        return ProduitResponse.builder()
                .id(produits.getId())
                .nom(produits.getNom())
                .description(produits.getDescription())
                .categorie(produits.getCategorie())
                .image(produits.getImage())
                .imageDas(produits.getImageDas())
                .prix(produits.getPrix())
                .marque(produits.getMarque())
                .type_produit(produits.getType_produit())
                .build();
    }
}
