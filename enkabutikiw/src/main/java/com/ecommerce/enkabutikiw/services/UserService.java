package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

// public  Integer isUserPresent(User user){
//     Integer user1 = userRepository.findByUsernameAndNomAndPrenom(user.getUsername(), user.getPrenom(), user.getNom());
//
//     return user1;
// }

 public User saveUser(User user){
     return  userRepository.save(user);
 }

}
