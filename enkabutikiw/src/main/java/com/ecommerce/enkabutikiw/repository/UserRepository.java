package com.ecommerce.enkabutikiw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.enkabutikiw.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
  Optional<User> findByUsernameOrEmail(String username, String email);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

    User findByEmail(String email);

    //User userrecup(User user);

  User findByTelephone(String emailOrTelephone);
}
