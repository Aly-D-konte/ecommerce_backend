package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.BoutiqueRepository;
import com.ecommerce.enkabutikiw.services.BoutiqueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoutiqueServiceimpl implements BoutiqueService {
    @Autowired
    private  final BoutiqueRepository boutiqueRepository;

    @Override
    public MessageResponse ajoutBoutique(Boutique boutique) {
        if (boutiqueRepository.findByNom(boutique.getNom())==null){
            boutiqueRepository.save(boutique);
            MessageResponse message = new MessageResponse("Boutique ajoutée avec succès");
            return message;

        }else {
            MessageResponse message = new MessageResponse("Cette Boutique existe déjà");

            return message;
        }

    }

    public MessageResponse supprimerBoutique(Long id) {

        Optional<Boutique> boutique = this.boutiqueRepository.findById(id);
        if (!boutique.isPresent()){
            MessageResponse message = new MessageResponse(" Categorie non trouvée ");
            return message;

        }else {
            this.boutiqueRepository.delete(boutique.get());
            MessageResponse message = new MessageResponse("Boutique supprimé avec succès");

            return message;
        }

    }



    @Override
    public Boutique ModifierBoutique(Boutique boutique, Long id) {
        Boutique modifierBoutique = boutiqueRepository.findById(id).get();
        modifierBoutique.setNom(boutique.getNom());
        modifierBoutique.setDescription(boutique.getDescription());
        modifierBoutique.setAdresse(boutique.getAdresse());
        modifierBoutique.setEtat(boutique.isEtat());
        modifierBoutique.setImage(boutique.getImage());
        return boutiqueRepository.saveAndFlush(modifierBoutique);
    }

    @Override
    public List<Boutique> liste() {
        return boutiqueRepository.findAll();
    }
}
