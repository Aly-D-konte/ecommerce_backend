package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.DTO.boutique.BoutiqueResponse;
import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface BoutiqueService {
    MessageResponse ajoutBoutique(Boutique boutique);
    MessageResponse supprimerBoutique(Long id);

    BoutiqueResponse ModifierBoutique(Boutique boutique, Long id);

   // List<Boutique> liste();
    List<BoutiqueResponse> mapToBoutique(List<Boutique> boutiqueList);
    Long nbreBoutique();
    List<Boutique> AfficherPArEtat(boolean etat);

    MessageResponse SetEtat(Boutique boutique, Long id);

    BoutiqueResponse mapToBoutique(Boutique boutique);
}
