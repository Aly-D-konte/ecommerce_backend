package com.ecommerce.enkabutikiw.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class JaimePas {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private  Long id;


    @ManyToOne
    private User user;
}


