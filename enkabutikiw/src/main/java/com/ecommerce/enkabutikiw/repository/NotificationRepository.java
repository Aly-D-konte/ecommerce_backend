package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification findByTitre(String titre);

    Optional<Notification> findById(Long id);
}
