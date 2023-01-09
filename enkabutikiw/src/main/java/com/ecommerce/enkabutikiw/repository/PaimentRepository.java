package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Paiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaimentRepository extends JpaRepository<Paiment, Long> {
}
