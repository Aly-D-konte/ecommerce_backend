package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
