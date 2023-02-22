package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Categorie;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitsRepository extends JpaRepository<Produits, Long> {
    Produits findByNom(String nom);

    Optional<Produits> findById(Long id);
    List<Produits> findByUser(User user);

}
