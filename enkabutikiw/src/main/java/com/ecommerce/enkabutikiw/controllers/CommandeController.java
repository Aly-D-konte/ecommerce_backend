package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.repository.CategorieRepository;
import com.ecommerce.enkabutikiw.repository.CommandeRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/commande")
@AllArgsConstructor
public class CommandeController {

    @Autowired
    private final CommandeRepository commandeRepository;

    @Autowired
    private final CommandeService commandeService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/check-out")
    public String checkout(Model model, User user){

        String username = user.getUsername();
        User users = userRepository.findByUsername(username);
        if(users.getNom().trim().isEmpty() || users.getPrenom().trim().isEmpty() ||
                users.getTelephone().trim().isEmpty() || users.getAdresse().trim().isEmpty()
             ){

            model.addAttribute("customer", username);
            model.addAttribute("error", "You must fill the information after checkout!");
            return "account";
        }else{
            model.addAttribute("customer", username);

        }
        return "checkout";
    }
}
