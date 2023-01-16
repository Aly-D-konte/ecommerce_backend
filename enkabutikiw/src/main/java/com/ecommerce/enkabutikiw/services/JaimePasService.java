package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.JaimePas;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

public interface JaimePasService {

    MessageResponse ajouterJaimePas(JaimePas jaimePas);
    MessageResponse supprimerJaimePas(Long id);
    JaimePas findById(Long id);
}
