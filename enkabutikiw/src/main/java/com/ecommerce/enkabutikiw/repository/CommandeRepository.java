package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
