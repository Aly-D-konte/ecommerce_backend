package com.ecommerce.enkabutikiw.repository;

import com.ecommerce.enkabutikiw.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
