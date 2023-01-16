package com.ecommerce.enkabutikiw.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jaime {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private  Long id;


    @ManyToOne
    private User user;

    @ManyToOne
    private Boutique boutique;
}
