package com.ecommerce.enkabutikiw.repository;

import java.util.Optional;

import com.ecommerce.enkabutikiw.models.ERole;
import com.ecommerce.enkabutikiw.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
