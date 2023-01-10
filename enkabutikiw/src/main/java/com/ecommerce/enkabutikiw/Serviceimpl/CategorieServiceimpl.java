package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.CategorieRepository;
import com.ecommerce.enkabutikiw.services.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategorieServiceimpl implements CategorieService {


    @Autowired
    private final CategorieRepository categorieRepository;
    @Override
    public MessageResponse ajoutCategorie(Categorie categorie) {
        if (categorieRepository.findByNom(categorie.getNom())==null){
            categorieRepository.save(categorie);
            MessageResponse message = new MessageResponse("Categorie ajoutée avec succès");
            return message;

        }else {
            MessageResponse message = new MessageResponse("Cette Categorie existe déjà");

            return message;
        }

    }

    @Override
    public MessageResponse supprimerCategorie(Long id) {

        Optional<Categorie> categorie = this.categorieRepository.findById(id);
        if (!categorie.isPresent()){
            MessageResponse message = new MessageResponse(" Categorie non trouvée ");
            return message;

        }else {
            this.categorieRepository.delete(categorie.get());
            MessageResponse message = new MessageResponse("Categorie supprimé avec succès");

            return message;
        }

    }

    @Override
    public Categorie ModifierCategorie(Categorie categorie, Long id) {

        Categorie modifierCategorie = categorieRepository.findById(id).get();
        categorie.setNom(categorie.getNom());
        categorie.setImage(categorie.getImage());
        return categorieRepository.saveAndFlush(modifierCategorie);
    }

    @Override
    public List<Categorie> liste() {
        return categorieRepository.findAll();
    }
}
