package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.PaimentRepository;

import java.util.List;

public interface PaimentService {

    MessageResponse ajoutPaiment(PaimentRepository paiment);
    MessageResponse supprimerPaiment(Long id);
    MessageResponse ModifierPaiment(PaimentRepository paiment, Long id);
    List<PaimentRepository> liste();
}
