package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

public interface PanierService {


    MessageResponse ajoutPanier(Panier panier);
    MessageResponse validerpanier();
}
