package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface BoutiqueService {
    MessageResponse ajoutBoutique(Boutique boutique);
    MessageResponse supprimerBoutique(Long id);
    Boutique ModifierBoutique(Boutique boutique, Long id);
    List<Boutique> liste();
    Long nbreBoutique();
    List<Boutique> AfficherPArEtat(boolean etat);

    MessageResponse SetEtat(Boutique boutique, Long id);
}
