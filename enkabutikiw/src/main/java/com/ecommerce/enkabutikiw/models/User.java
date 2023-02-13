package com.ecommerce.enkabutikiw.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
@Data
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 20)
  private String username;

  @Size(max = 20)
  private String prenom;

  @Size(max = 20)
  private String nom;

  @Size(max = 20)
  private String telephone;

  @Size(max = 20)
  private String adresse;

  @Size(max = 20)
  private String genre;

  private String image;



  @Size(max = 50)
  @Email
  private String email;

  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();



  public User() {
  }

  public User(String username, String email, String password, String prenom,String nom, String telephone, String adresse, String genre, String image) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.prenom = prenom;
    this.nom = nom;
    this.adresse = adresse;

    this.telephone = telephone;
    this.genre = genre;

    this.image = image;

  }

}
