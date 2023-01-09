package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface BoutiqueService {
    MessageResponse ajoutBoutique(Boutique boutique);
    MessageResponse supprimerBoutique(Long id);
    Boutique ModifierBoutique(Boutique boutique, Long id);
    List<Boutique> liste();
}
