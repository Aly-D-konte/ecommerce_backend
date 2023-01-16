package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface PanierRepository extends JpaRepository<Panier, Long> {

    Panier findByProduits(Produits produits);


    Boolean existsByProduits(Produits produits);

//    @Query(value = "SELECT SUM(panier.quantite) AS TotalProduit, SUM(panier.totalproduit) AS PrixTotaux FROM panier,users WHERE panier.user_id = users.id AND users.id=:user_id",nativeQuery = true)
//    public Object detail(Long user_id);

}
