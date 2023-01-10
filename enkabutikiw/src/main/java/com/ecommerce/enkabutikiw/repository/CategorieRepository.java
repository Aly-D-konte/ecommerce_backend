package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Boutique;
import com.ecommerce.enkabutikiw.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {


    Categorie findByNom(String nom);

    Optional<Categorie> findById(Long id);
}
