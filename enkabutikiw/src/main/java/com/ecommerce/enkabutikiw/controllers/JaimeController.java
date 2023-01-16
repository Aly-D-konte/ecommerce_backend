package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.img.SaveImage;
import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.models.Jaime;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.BoutiqueRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.JaimeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/jaime")
@AllArgsConstructor
public class JaimeController {

    @Autowired
    private final JaimeService jaimeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoutiqueRepository boutiqueRepository;

    @PostMapping("/ajouter/{id_user}/{boutique_id}")
    public Object ajouter(Jaime jaime, @PathVariable("id_user") Long id_user, @PathVariable("boutique_id") Long boutique_id) {
        Jaime jaime1 = new Jaime();
        try {
            User user= userRepository.findById(id_user).get();
            Boutique boutique = boutiqueRepository.findById(boutique_id).get();

            jaime.setUser(user);
            jaime.setBoutique(boutique);

            return jaimeService.ajouterJaime(jaime, id_user, boutique_id);
        }catch (Exception e){
            return e.getMessage();
        }

    }



    @DeleteMapping("/supprimer/{id}")
    public MessageResponse supprimerJaime(@PathVariable("id") Long id){
        return jaimeService.supprimerJaime(id);
    }
   @GetMapping("/liste")
   public List<Jaime> list(){

        return jaimeService.liste();
   }




}
