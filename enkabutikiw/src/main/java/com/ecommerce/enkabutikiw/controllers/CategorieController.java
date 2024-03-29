package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.img.ConfigImage;
import com.ecommerce.enkabutikiw.img.Projetimage;
import com.ecommerce.enkabutikiw.img.SaveImage;
import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.CategorieRepository;
import com.ecommerce.enkabutikiw.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/categorie")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CategorieController {

    @Autowired
    CategorieService categorieService;
    @Autowired
    private CategorieRepository categorieRepository;



    @PostMapping("/ajouter")
   // @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')   ")

    public MessageResponse ajoutCategorie(@Param("nom") String nom,
                                          @Param("image") MultipartFile image
                                         ) throws IOException {
        Categorie categorie = new Categorie();
       // String nomfile = StringUtils.cleanPath(image.getOriginalFilename());
        String nomfile = StringUtils.cleanPath(image.getOriginalFilename()) ;

        categorie.setNom(nom);
        categorie.setImage(nomfile);
        categorie.setImageDas(nomfile);

        if (categorieRepository.findByNom(nom) == null){
            String uploaDir = "C:\\Users\\sadjo\\OneDrive\\Bureau\\ODK\\flutter_enkabutikiw\\flutter_frontend\\assets\\categorie";

           // String uploaDir = "C:\\Users\\sadjo\\OneDrive\\Bureau\\ODK\\flutter_enkabutikiw\\flutter_frontend\\assets\\categorie";
            ConfigImage.saveimg(uploaDir, nomfile, image);

//            String uploaDir = "C:\\Users\\adkonte\\Documents\\ecommerce_backend\\enkabutikiw\\src\\test\\Images";
//           ConfigImage.saveimg(uploaDir, nomfile, file);
            categorie.setImageDas(Projetimage.save(image,image.getOriginalFilename()));

            //categorie.setImage(Projetimage.save(image,image.getOriginalFilename()));
            return categorieService.ajoutCategorie(categorie);

        }else {
            MessageResponse message = new MessageResponse("Categorie existe déja");
            return message;
        }


    }



    @GetMapping("/liste")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN') ")

    public List<Categorie> list(Categorie categorie){
        return categorieService.liste();
    }



    @PutMapping("/modifier/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') ")

    public Categorie modifierCategorie(@Param("Categorie") Categorie categorie, @PathVariable Long id){
        return categorieService.ModifierCategorie(categorie, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN') ")

    @DeleteMapping("/supprimer/{id}")
    public MessageResponse supprimerCategorie(@PathVariable Long id){
        return categorieService.supprimerCategorie(id);
    }

}



