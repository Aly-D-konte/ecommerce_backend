package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.BoutiqueRepository;
import com.ecommerce.enkabutikiw.services.BoutiqueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public MessageResponse supprimerBoutique(Long id) {
        if (boutiqueRepository.findById(id) !=null){
            boutiqueRepository.deleteById(id);
            MessageResponse message = new MessageResponse("Boutique supprimée avec succès");
            return message;

        }else {
            MessageResponse message = new MessageResponse("Boutique non trouvée");

            return message;
        }
    }

    @Override
    public Boutique ModifierBoutique(Boutique boutique, Long id) {
        Boutique modifierBoutique = boutiqueRepository.findById(id).get();
        modifierBoutique.setNom(boutique.getNom());
        modifierBoutique.setDescription(boutique.getDescription());
        modifierBoutique.setAdresse(boutique.getAdresse());
        modifierBoutique.setType(boutique.isType());
        modifierBoutique.setImage(boutique.getImage());
        return boutiqueRepository.saveAndFlush(modifierBoutique);
    }

    @Override
    public List<Boutique> liste() {
        return boutiqueRepository.findAll();
    }
}
