package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.UserModifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserModifierServiceimpl implements UserModifierService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Override
    public MessageResponse Modifier(User user, Long id) {

        if(userRepository.findById(id) == null){
            MessageResponse message = new MessageResponse("Cet utilisateur n'existe pas !");
            return message;
        }
        else{
            User user1 = userRepository.findById(id).get();
            user1.setNom(user.getNom());
            user1.setPrenom(user.getPrenom());
            user1.setAdresse(user.getAdresse());
            user1.setEmail(user.getEmail());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setUsername(user.getUsername());
            //user1.setAvatar(user.getAvatar());

            this.userRepository.save(user1);
            MessageResponse message = new MessageResponse("Modification reussie avec succès !");
            return message;

        }
    }

    @Override
    public MessageResponse ModifierAvatar(User user, Long id) {

        if(userRepository.findById(id) != null){
            User user1 = userRepository.findById(id).get();

            user1.setImage(user.getImage());
            userRepository.save(user1);
            MessageResponse message = new MessageResponse("Image modifiée avec succès !");
            return message;
        }
        else{

            MessageResponse message = new MessageResponse("Image non trouver !");
            return message;

        }
    }
}
