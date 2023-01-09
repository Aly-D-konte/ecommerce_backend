package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.img.ConfigImage;
import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.BoutiqueRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.BoutiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/boutique")

public class BoutiqueController {

    @Autowired
    BoutiqueService boutiqueService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BoutiqueRepository boutiqueRepository;

    @PostMapping("/ajouter")
    public MessageResponse ajoutBoutique(@Param("nom") String nom, @Param("description") String description, @Param("adresse") String adresse,@Param("image") String image,@Param("type") boolean type, @Param("file") MultipartFile file) throws IOException {
        Boutique boutique = new Boutique();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        boutique.setNom(nom);
        boutique.setDescription(description);
        boutique.setAdresse(adresse);
        boutique.setImage(nomfile);
        boutique.setType(type);
        boutique.setUser(userRepository.findById(1L).get());

        if (boutiqueRepository.findByNom(nom) == null){

            String uploaDir = "C:\\Users\\adkonte\\Documents\\ecommerce_backend\\enkabutikiw\\src\\test\\Images";
           ConfigImage.saveimg(uploaDir, nomfile, file);
            return boutiqueService.ajoutBoutique(boutique);

        }else {
            MessageResponse message = new MessageResponse("Boutique existe déja");
            return message;
        }


    }




    @GetMapping("liste")
    public List<Boutique> list(Boutique boutique){
        return boutiqueService.liste();
    }

    @PutMapping("/modifier/{id}")
    public Boutique modifierBoutique(@Param("boutique") Boutique boutique, @PathVariable Long id){
        return boutiqueService.ModifierBoutique(boutique, id);
    }

}
