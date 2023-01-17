package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.img.SaveImage;
import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.BoutiqueRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.BoutiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('SUPER_ADMIN')")

    public MessageResponse ajoutBoutique(@Param("nom") String nom,
                                         @Param("description") String description,
                                         @Param("adresse") String adresse,
                                         @Param("user_id") Long user_id,
                                        // @PathVariable("user_id") Long user_id,
                                         @Param("image") String image, @Param("type") boolean etat, @Param("file") MultipartFile file) throws IOException {
        Boutique boutique = new Boutique();
     // String nomfile = StringUtils.cleanPath(file.getOriginalFilename()) ;
        boutique.setNom(nom);
        boutique.setDescription(description);
        boutique.setAdresse(adresse);
      //  boutique.setImage(nomfile);
        boutique.setEtat(boutique.isEtat());
        //boutique.setUser(user_id);
      boutique.setUser(userRepository.findById(user_id).get());

        if (boutiqueRepository.findByNom(nom) == null){

//            String uploaDir = "C:\\Users\\adkonte\\Documents\\ecommerce_backend\\enkabutikiw\\src\\test\\Images";
//           ConfigImage.saveimg(uploaDir, nomfile, file);
            boutique.setImage(SaveImage.save(file,
                    file.getOriginalFilename()));
            return boutiqueService.ajoutBoutique(boutique);

        }else {
            MessageResponse message = new MessageResponse("Boutique existe déja");
            return message;
        }


    }




    @GetMapping("/liste")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN') ")

    public List<Boutique> list(){
        return boutiqueService.liste();
    }

    @PutMapping("/modifier/{id}")

    @PreAuthorize("hasRole('SUPER_ADMIN')")

    public Boutique modifierBoutique(@Param("boutique") Boutique boutique, @PathVariable Long id){
        return boutiqueService.ModifierBoutique(boutique, id);
    }




    @DeleteMapping("/supprimer/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")

    public MessageResponse supprimerBoutique(@PathVariable("id") Long id){
        return boutiqueService.supprimerBoutique(id);
    }

}
