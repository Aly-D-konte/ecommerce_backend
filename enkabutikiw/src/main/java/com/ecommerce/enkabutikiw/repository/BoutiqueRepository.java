package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {
    Boutique findByNom(String nom);

    @Query( value ="SELECT COUNT(*) FROM boutique", nativeQuery = true)
    Long nbreBoutique();
    List<Boutique> findByEtat(boolean etat);

}
