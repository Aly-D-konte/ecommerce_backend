package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Commande;
import com.ecommerce.enkabutikiw.models.Paiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaimentRepository extends JpaRepository<Paiment, Long> {

}
