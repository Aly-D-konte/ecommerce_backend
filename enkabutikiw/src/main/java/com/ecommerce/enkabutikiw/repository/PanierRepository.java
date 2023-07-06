package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PanierRepository extends JpaRepository<Panier, Long> {

    Panier findByProduits(Produits produits);


    Boolean existsByProduits(Produits produits);

   List<Panier> findByUser(User user);

}
