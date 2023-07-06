package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.Type_produit;
import com.ecommerce.enkabutikiw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository

public interface TypeProduitRepository extends JpaRepository<Type_produit, Long> {
    Type_produit findByNom(String nom);

    Optional<Type_produit> findById(Long id);
  //  List<Produits> findByUser(User user);
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO type_produit (nom) VALUES ('Local'), ('Industriel')", nativeQuery = true)
  void createTypeProduit();
}
