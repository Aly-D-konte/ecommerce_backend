package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    Commande findByCode(String code);
    Optional<Commande> findById(Long id);

}
