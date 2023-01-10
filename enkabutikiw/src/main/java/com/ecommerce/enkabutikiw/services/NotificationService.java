package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.Notification;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;

import java.util.List;

public interface NotificationService {

    MessageResponse ajoutNotification(Notification notification);
    MessageResponse supprimerNotification(Long id);
    MessageResponse ModifierNotification(Notification notification, Long id);
    Notification envoyerNotification(Long user_id, Notification notification);
    List<Notification> liste();
}

