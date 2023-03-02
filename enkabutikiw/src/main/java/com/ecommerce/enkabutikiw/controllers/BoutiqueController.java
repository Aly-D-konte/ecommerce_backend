package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.img.Projetimage;
import com.ecommerce.enkabutikiw.img.SaveImage;
import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.BoutiqueRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.BoutiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boutique")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")


public class BoutiqueController {

    @Autowired
    BoutiqueService boutiqueService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BoutiqueRepository boutiqueRepository;


    //@PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/ajouter")
    public MessageResponse ajoutBoutique(@Param("nom") String nom,
                                         @Param("description") String description,
                                         @Param("adresse") String adresse,
                                         @Param("user_id") Long user_id,
                                        // @PathVariable("user_id") Long user_id,
                                         @Param("image") String image, @Param("etat") boolean etat, @Param("file") MultipartFile file) throws IOException {
        Boutique boutique  = new Boutique();
     // String nomfile = StringUtils.cleanPath(file.getOriginalFilename()) ;
        boutique.setNom(nom);
        boutique.setDescription(description);
        boutique.setAdresse(adresse);
      //  boutique.setImage(nomfile);
        boutique.setEtat(boutique.isEtat());

        //boutique.setUser(user_id);boutique.setImage(boutique.getImage());
      boutique.setUser(userRepository.findById(user_id).get());

        if (boutiqueRepository.findByNom(nom) == null){

//            String uploaDir = "C:\\Users\\adkonte\\Documents\\ecommerce_backend\\enkabutikiw\\src\\test\\Images";
//           ConfigImage.saveimg(uploaDir, nomfile, file);
            System.out.println("C'est mon image " +image);
            boutique.setImage(Projetimage.save(file,
                    file.getOriginalFilename()));
            return boutiqueService.ajoutBoutique(boutique);

        }else {
            MessageResponse message = new MessageResponse("Boutique existe déja");
            return message;
        }


    }




    @GetMapping("/liste")
  //  @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SUPER_ADMIN') ")

    public List<Boutique> list(){
        return boutiqueService.liste();
    }

    @PutMapping("/modifier/{id}")

   // @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Boutique modifierBoutique(@Param("boutique") Boutique boutique, @PathVariable Long id){
        return boutiqueService.ModifierBoutique(boutique, id);
    }
    @GetMapping("/getbyid/{boutique}")

    public Boutique getboutq(@PathVariable Boutique boutique){
        return boutiqueRepository.findById(boutique.getId()).get();
    }




    @DeleteMapping("/supprimer/{id}")
   // @PreAuthorize("hasRole('SUPER_ADMIN')")

    public MessageResponse supprimerBoutique(@PathVariable("id") Long id){
        return boutiqueService.supprimerBoutique(id);
    }

    @GetMapping("/nombre")
    public Long nbreBoutique() {
        return boutiqueService.nbreBoutique();
    }


    @PatchMapping("/etat/{id}")
    public MessageResponse SetEtat(@RequestBody Boutique boutique, @PathVariable("id") Long id){
        if(this.boutiqueRepository.findById(id) == null){

            MessageResponse message = new MessageResponse("Boutique n'existe pas !");
            return message;
        }
        else{
            return this.boutiqueService.SetEtat(boutique,id);
        }
    }


    //A


    @GetMapping("/{boutique}/{etat}")
    public List<Boutique> findByEtat(@PathVariable("boutique") Boutique boutique, @PathVariable("etat") boolean etat){
        return boutiqueService.AfficherPArEtat(etat);

    }


    @GetMapping("boutiqueByUser/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') ")

    public List<Boutique> boutiqueByUser(@PathVariable("id") User id){
        return boutiqueRepository.findByUser(id);

    }
}
