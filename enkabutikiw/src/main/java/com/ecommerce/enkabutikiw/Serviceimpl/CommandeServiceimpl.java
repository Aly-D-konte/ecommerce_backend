package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.Commande;
import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.CategorieRepository;
import com.ecommerce.enkabutikiw.repository.CommandeRepository;
import com.ecommerce.enkabutikiw.repository.ProduitsRepository;
import com.ecommerce.enkabutikiw.services.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommandeServiceimpl implements CommandeService {

    @Autowired
    private  CommandeRepository commandeRepository;
    private final CategorieRepository categorieRepository;
    @Autowired
    private final ProduitsRepository produitsRepository;


    @Override
    public MessageResponse ajouteCommande(Commande commande,Long id ) {

        if (commandeRepository.findByCode(commande.getCode())==null){
            commande.setDate(new Date());
            Optional<Produits> produit = produitsRepository.findById(id);

            if (produit.isPresent())
            {

            }
            commandeRepository.save(commande);
            MessageResponse message = new MessageResponse("Commande ajoutée avec succès");
            return message;

        }else {
            MessageResponse message = new MessageResponse("Cette commande existe déjà");

            return message;
        }
    }

    @Override
    public MessageResponse supprimerCommande(Long id) {
        Optional<Commande> commande = this.commandeRepository.findById(id);
        if (!commande.isPresent()){
            MessageResponse message = new MessageResponse(" Commande non trouvée ");
            return message;

        }else {
            this.commandeRepository.delete(commande.get());
            MessageResponse message = new MessageResponse("Commande supprimé avec succès");

            return message;
        }
    }

    //checkout commande


    @Override
    public Commande ModifierCommande(Commande commande, Long id) {
        Commande modifierCommande = commandeRepository.findById(id).get();
        commande.setCode(commande.getCode());
        commande.setDate(commande.getDate());

        return commandeRepository.saveAndFlush(modifierCommande);    }

    @Override
    public List<Commande> liste() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande findByCode(String code) {
        return commandeRepository.findByCode(code);
    }

    @Override
    public float save(Produits id, List<Panier> paniers) {
        return 0;
    }

    @Override
    public MessageResponse commander(Commande commande,List<Panier> paniers, Long produits_id) {

        commande.setDate(new Date());
//
//        for (Panier panier : paniers) {
//            Produits produits = produitsRepository.findById(panier.getProduits()).orElseThrow(() -> new ProduitNotFoundException());
//            if (panier.getQuantite() > produits.getQuantite_disponible()) {
//                MessageResponse message = new MessageResponse(" La qualité est commandée est supérieure au stock ");
//                return  message;
//            }
//            Long newQuantity = produits.getQuantite_disponible() - panier.getQuantite();
//            produits.setQuantite_disponible(newQuantity);
//            produitsRepository.save(produits);
//        }
        commandeRepository.save(commande);
        MessageResponse message = new MessageResponse(" Commande effectué avec success ");

        return message;
    }
//    @Override
//    public float save(Produits id, List<Panier> paniers) {
//        //Commande commandes = findByCode(id.getCode());
//        float totalMontantPanier = 0f;
//        float montantSimplePanier = 0f;
//        int quantite = 0;
//
//        for (Panier panier : paniers){
//          //  Produits produit_id = panier.getProduits().get(id);
//            Optional<Produits> produit = produitsRepository.findById(id);
//            if (panier.getProduits().)
//
//            if (produit.isPresent()){
//                Produits produit1 = produit.get();
//                if (produit1.getQuantite() < panier.getQuantite()){
//                   montantSimplePanier = produit1.getPrix() * produit1.getQuantite();
//                    panier.setQuantite(produit1.getQuantite());
//                } else {
//                    montantSimplePanier = panier.getQuantite() * produit1.getPrix();
//                    quantite = (int) (produit1.getQuantite() - panier.getQuantite());
//              }
//                totalMontantPanier = totalMontantPanier + montantSimplePanier;
//                produit1.setQuantite((long) quantite);
//                quantite = 0;
//                panier.setProduits(produit1.getNom());
//                panier.setTotalproduit((long) montantSimplePanier);
//                commandeRepository.save(produit1);
//            }
//        }
//        return totalMontantPanier;
//    }

//    @Override
//    public float getPanier(List<Panier> paniers) {
//        float totalMontantPanier = 0f;
//        float montantSimplePanier = 0f;
//        int quantite = 0;
//
//        for (Panier panier : paniers){
//           // int produit_id = panier.getProduits();
//            Optional<Produits> produit = produitsRepository.findById(panier.getId());
//            if (produit.isPresent()){
//                Produits produit1 = produit.get();
//                if (produit1.getQuantite() < panier.getQuantite()){
//                    montantSimplePanier = produit1.getPrix() * produit1.getQuantite();
//                    panier.setQuantite(produit1.getQuantite());
//                } else {
//                    montantSimplePanier = panier.getQuantite() * produit1.getPrix();
//                    quantite = produit1.getQuantite() - panier.getQuantite();
//                }
//                totalMontantPanier = totalMontantPanier + montantSimplePanier;
//                produit1.setQuantite(quantite);
//                quantite = 0;
//             //  panier.setProduits(produit1.getNom());
//                panier.setTotalproduit((long) montantSimplePanier);
//                 commandeRepository.saveAll(produit1);
//
//
//            }
//
//        }
//        return totalMontantPanier;
//    }


}