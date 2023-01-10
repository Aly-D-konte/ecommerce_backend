package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.Commande;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.CategorieRepository;
import com.ecommerce.enkabutikiw.repository.CommandeRepository;
import com.ecommerce.enkabutikiw.services.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommandeServiceimpl implements CommandeService {

    @Autowired
    private final CommandeRepository commandeRepository;
    private final CategorieRepository categorieRepository;


    @Override
    public MessageResponse ajouteCommande(Commande commande) {
        if (commandeRepository.findByCode(commande.getCode())==null){
            commandeRepository.save(commande);
            MessageResponse message = new MessageResponse("Commande ajoutée avec succès");
            return message;

        }else {
            MessageResponse message = new MessageResponse("Cette commande existe déjà");

            return message;
        }
    }

    @Override
    public MessageResponse supprimerCommande(Long id) {
        Optional<Commande> commande = this.commandeRepository.findById(id);
        if (!commande.isPresent()){
            MessageResponse message = new MessageResponse(" Commande non trouvée ");
            return message;

        }else {
            this.commandeRepository.delete(commande.get());
            MessageResponse message = new MessageResponse("Commande supprimé avec succès");

            return message;
        }
    }

    @Override
    public Commande ModifierCommande(Commande commande, Long id) {
        Commande modifierCommande = commandeRepository.findById(id).get();
        commande.setCode(commande.getCode());
        commande.setDate(commande.getDate());
        commande.setMontant(commande.getMontant());
        commande.setQuantite(commande.getQuantite());
        return commandeRepository.saveAndFlush(modifierCommande);    }

    @Override
    public List<Commande> liste() {
        return commandeRepository.findAll();
    }
}
