package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    Commande findByCode(String code);
    List<Commande> findByUser(User user);
    //List<Commande> findByStatut (Statut statut);

    Commande findByIdCommande(Long idCommande);

    List<Object> findByStatut (Statut statut);

}
