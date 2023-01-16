package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.JaimePas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JaimePasRepository extends JpaRepository<JaimePas, Long> {
}
