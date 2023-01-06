package com.ecommerce.enkabutikiw.models;

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
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 20)
  private String prenom;

  @NotBlank
  @Size(max = 20)
  private String telephone;

  @NotBlank
  @Size(max = 20)
  private String adresse;

  @NotBlank
  @Size(max = 20)
  private String genre;

  @NotBlank
  @Size(max = 20)
  private String image;



  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password, String prenom, String telephone, String adresse, String genre, String image) {
    this.username = username;
    this.telephone = telephone;
    this.genre = genre;
    this.adresse = adresse;
    this.email = email;
    this.image = image;
    this.password = password;
    this.prenom = prenom;

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getTelephone() {
    return telephone;
  }

  public String getAdresse() {
    return adresse;
  }

  public String getGenre() {
    return genre;
  }

  public String getImage() {
    return image;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void getPrenom(String prenom) {
    this.prenom = prenom;
  }

  public void getTelephone(String telephone) {
    this.telephone = telephone;
  }

  public void getAdresse(String adresse) {
    this.adresse = adresse;
  }

  public void getGenre(String genre) {
    this.genre = genre;
  }

  public void getImage(String image) {
    this.image = image;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
