package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Jaime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JaimeRepository extends JpaRepository<Jaime, Long> {

    Jaime findByid(Long id);
}
