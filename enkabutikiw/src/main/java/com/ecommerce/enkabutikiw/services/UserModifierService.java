package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface UserModifierService {

    MessageResponse  Modifier(User user, Long id);
    MessageResponse ModifierAvatar(User user, Long id);

    List<User> liste();
}
