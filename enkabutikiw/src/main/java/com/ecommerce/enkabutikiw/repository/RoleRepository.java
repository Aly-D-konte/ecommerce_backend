package com.ecommerce.enkabutikiw.repository;

import java.util.Optional;

import com.ecommerce.enkabutikiw.models.ERole;
import com.ecommerce.enkabutikiw.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO roles (name) VALUES ('ROLE_SUPER_ADMIN'), ('ROLE_ADMIN'), ('ROLE_USER'), ('ROLE_LIVREUR')", nativeQuery = true)
  void createRole();
}
