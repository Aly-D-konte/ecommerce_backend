package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.models.Type_produit;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.CategorieRepository;
import com.ecommerce.enkabutikiw.repository.TypeProduitRepository;
import com.ecommerce.enkabutikiw.services.TypeProduitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TypeProduitServiceImpl implements TypeProduitService {

    @Autowired
    private final TypeProduitRepository typeProduitRepository;

    @Override
    public MessageResponse ajoutType(Type_produit type_produit) {
        if (typeProduitRepository.findByNom(type_produit.getNom())== null){
            //    boutiqueRepository.save(produit.getBoutique());
            typeProduitRepository.save(type_produit);
            MessageResponse message = new MessageResponse("Type_produit ajoutée avec succès");
            return message;

        }else {
            MessageResponse message = new MessageResponse("Cet type_produit existe déjà");

            return message;
        }
    }

    @Override
    public MessageResponse supprimerType(Long id) {
        if (typeProduitRepository.findById(id) !=null){
            typeProduitRepository.deleteById(id);
            MessageResponse message = new MessageResponse("Type_produit supprimé avec succès");
            return message;

        }else {
            MessageResponse message = new MessageResponse("Type_produit non trouvé");

            return message;
        }     }

    @Override
    public Type_produit ModifierType(Type_produit type_produit, Long id) {
        Type_produit modifierTypeProduit = typeProduitRepository.findById(id).get();
        type_produit.setNom(type_produit.getNom());
        return typeProduitRepository.saveAndFlush(modifierTypeProduit);    }

    @Override
    public List<Type_produit> liste() {
        return typeProduitRepository.findAll();
    }
}
