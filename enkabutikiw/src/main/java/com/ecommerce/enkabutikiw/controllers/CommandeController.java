package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.Serviceimpl.UserServiceimpl;
import com.ecommerce.enkabutikiw.models.Commande;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.Statut;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.CommandeRepository;
import com.ecommerce.enkabutikiw.repository.PanierRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.CommandeService;
import com.ecommerce.enkabutikiw.services.PanierService;
import com.ecommerce.enkabutikiw.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/commande")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

@AllArgsConstructor
public class CommandeController {

        @Autowired
        private final CommandeRepository commandeRepository;

        @Autowired
        private final CommandeService commandeService;


        @Autowired
        private final PanierService panierService;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private PanierRepository panierRepository;

        @Autowired
        private UserService userService;

        @Autowired
        private UserServiceimpl userServiceimpl;


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

    //    @PostMapping("/ajouter")
    //    public MessageResponse ajoutCommande(@RequestBody CommandeDto commandeDto){
    //       // Commande commandes = new Commande();
    //
    //        //float quantitepanier = panierRepository.;
    //        User user = new User();
    //        Integer userPresentDb =userService.isUserPresent(user);
    //        if (userPresentDb !=null){
    //            user.setId(Long.valueOf(userPresentDb));
    //
    //        }else {
    //            user = userService.saveUser(user);
    //        }
    //        Commande commande1 = new Commande();
    //        commande1 = commandeService.ajouteCommande(commande1);
    //
    //        return  " Commmande";



        @PostMapping("/ajouter/{panier_id}")
        public  MessageResponse ajouterCommande(@RequestBody Commande commande, @PathVariable("panier_id") Long panier_id){
            if (commandeRepository.findByCode(commande.getCode())==null){
                Commande commandes = new Commande();
                commande.setCode(commande.getCode());
                commande.setPanier(panierRepository.findById(panier_id).get());
                return   commandeService.ajouteCommande(commande, panier_id);
            }
            else {
                MessageResponse message = new MessageResponse("Cette reference existe déja");
                return message;
            }

        }

        @GetMapping("/liste")
        public List<Commande> list(){
            return commandeService.liste();
        }


        @PostMapping("/commander/{panier_id}")
        public MessageResponse commande(@RequestBody Commande commande, @PathVariable("panier_id") Long panier_id){
            if (commandeRepository.findByCode(commande.getCode())==null){
                commande.setCode(commande.getCode());
                commande.setPanier(panierRepository.findById(panier_id).get());
                return   commandeService.ajouteCommande(commande, panier_id);
            }
            else {
                MessageResponse message = new MessageResponse("Cette reference existe déja");
                return message;
            }
        }

    @GetMapping("CommandeParUser/{id}")
    //@PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') ")

    public List<Commande> produitByUser(@PathVariable("id") User id){
        return commandeRepository.findByUser(id);

    }

    @PostMapping("/ENCOURS/{idCommande}")
    public Commande Commandeencours(@PathVariable Long idCommande) throws IOException {
            return  commandeService.commandeencours(idCommande);

    }
    @PostMapping("/ANNULER/{idCommande}")
    public Commande Commandeannuler(@PathVariable Long idCommande) throws IOException {
        return  commandeService.commandeannuler(idCommande);

    }
    @PostMapping("/LIVRER/{idCommande}")
    public Commande Commandelivrer(@PathVariable Long idCommande) throws IOException {
        return  commandeService.commandelivrer(idCommande);

    }

    @GetMapping("/regarder/{statut}")
    public Object VoirStatut(@PathVariable String statut){
        if (statut.equals("encours")){
            return commandeService.voirStatut(Statut.ENCOURS);
        }
        else if (statut.equals("annuler")) {
            return commandeService.voirStatut(Statut.ANNULER);
        }else if (statut.equals("livrer")){
            return commandeService.voirStatut(Statut.LIVRER);
        } else {
            return "ssss dddd";
        }
    }


    @PostMapping("/ajouter/{panier_id}/{user_id}")
    public  MessageResponse ajouter(@RequestBody Commande commande, @PathVariable("panier_id") Long panier_id, @PathVariable("user_id") User user_id){
        if (commandeRepository.findByCode(commande.getCode())==null){
            Commande commandes = new Commande();

            commande.setCode(commande.getCode());
            commande.setStatut(Statut.ENCOURS);
            commande.setUser(user_id);
            commande.setPanier(panierRepository.findById(panier_id).get());
            return   commandeService.ajouteCommande(commande, panier_id);
        }
        else {
            MessageResponse message = new MessageResponse("Cette reference existe déja");
            return message;
        }

    }
}