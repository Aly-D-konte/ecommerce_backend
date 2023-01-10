package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.PaimentRepository;
import com.ecommerce.enkabutikiw.services.PaimentService;

import java.util.List;

public class PaimentServiceimpl implements PaimentService {
    @Override
    public MessageResponse ajoutPaiment(PaimentRepository paiment) {
        return null;
    }

    @Override
    public MessageResponse supprimerPaiment(Long id) {
        return null;
    }

    @Override
    public MessageResponse ModifierPaiment(PaimentRepository paiment, Long id) {
        return null;
    }

    @Override
    public List<PaimentRepository> liste() {
        return null;
    }
}
