package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.Jaime;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface JaimeService {

    Jaime ajouterJaime(Jaime jaime, Long user_id , Long boutique_id);
    MessageResponse supprimerJaime(Long id);

    List<Jaime> liste();
}
