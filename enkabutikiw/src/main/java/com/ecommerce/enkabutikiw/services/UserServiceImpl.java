package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.DTO.user.UserResponse;
import com.ecommerce.enkabutikiw.Serviceimpl.EmailConstructor;
import com.ecommerce.enkabutikiw.models.Role;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailConstructor emailConstructor;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void resetPassword(User user) {
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, password));
    }

    @Override
    public void updateUserPassword(User user, String newPassword) {
        String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, newPassword));

    }
    @Override
    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public UserResponse modifier(User user, Long id) {

        User modifierUser = userRepository.findById(id).get();
        modifierUser.setNom(user.getNom());
        modifierUser.setUsername(user.getUsername());
        modifierUser.setPrenom(user.getPrenom());
        modifierUser.setEmail(user.getEmail());
        modifierUser.setTelephone(user.getTelephone());
        modifierUser.setGenre(user.getGenre());
        modifierUser.setAdresse(user.getAdresse());
        modifierUser.setPassword(user.getPassword());
        modifierUser.setRoles(user.getRoles());
        modifierUser.setImage(user.getImage());
        modifierUser.setPanier(user.getPanier());

        modifierUser = userRepository.saveAndFlush(modifierUser);
        return this.mapToUser(modifierUser);    }

    @Override
    public List<UserResponse> mapToUserList(List<User> userList) {
        List<UserResponse> UserResponseList = new ArrayList<>();
        for (User user: userList){
            UserResponseList.add(this.mapToUser(user));
        }
        return UserResponseList;
    }

    @Override
    public UserResponse mapToUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .adresse(user.getAdresse())
                .image(user.getImage())
                .email(user.getEmail())
                .genre(user.getGenre())
                .telephone(user.getTelephone())
                .roles((List<Role>) user.getRoles())
                .panier(user.getPanier())
                .build();    }


    @Override
    public UserResponse getUserById(Long id) {
        return this.mapToUser(userRepository.findById(id).get());
    }

}
