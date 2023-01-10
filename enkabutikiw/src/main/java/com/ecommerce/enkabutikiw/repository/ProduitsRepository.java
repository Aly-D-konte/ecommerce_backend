package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.models.Produits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProduitsRepository extends JpaRepository<Produits, Long> {
    Produits findByNom(String nom);

    Optional<Produits> findById(Long id);

}
