package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.BoutiqueRepository;
import com.ecommerce.enkabutikiw.repository.ProduitsRepository;
import com.ecommerce.enkabutikiw.services.ProduitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Produits ModifierProduit(Produits produit, Long id) {


        Produits modifierProduit = produitsRepository.findById(id).get();
        modifierProduit.setNom(produit.getNom());
        modifierProduit.setDescription(produit.getDescription());
        modifierProduit.setCategorie(produit.getCategorie());
        modifierProduit.setType(produit.getType());
        modifierProduit.setImage(produit.getImage());
        modifierProduit.setCapacite(produit.getCapacite());
        modifierProduit.setMarque(produit.getMarque());
        modifierProduit.setModele(produit.getModele());
        modifierProduit.setUser(produit.getUser());
        modifierProduit.setPrix(produit.getPrix());
        return produitsRepository.saveAndFlush(modifierProduit);    }

    @Override
    public List<Produits> liste() {
        return produitsRepository.findAll();
    }
}
