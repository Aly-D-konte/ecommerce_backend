package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.Notification;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.NotificationRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceimpl implements NotificationService {

    @Autowired
    private final NotificationRepository notificationRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public MessageResponse ajoutNotification(Notification notification) {
        return null;
    }

    @Override
    public MessageResponse supprimerNotification(Long id) {
        return null;
    }

    @Override
    public MessageResponse ModifierNotification(Notification notification, Long id) {
        return null;
    }


   //Methode pour envoyer une notification à l'utilisateur
    //debutttttttttttttttttttttt
    @Override
    public Notification envoyerNotification(Long user_id, Notification notification) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() ->new UsernameNotFoundException("User not found"));

        Notification notifications = new Notification(notification, LocalDateTime.now(), user);


        return notificationRepository.save(notification);
    }
//finnnnnnnnnnnnnnnnnnnnnnnnnn
    @Override
    public List<Notification> liste() {
        return notificationRepository.findAll();
    }
}
