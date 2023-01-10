package com.ecommerce.enkabutikiw.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String message;
    private LocalDateTime date;

    public Notification(Notification notification, LocalDateTime now, User user) {
    }
}
