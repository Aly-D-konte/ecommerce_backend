package com.ecommerce.enkabutikiw.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(min = 3, max = 20)
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

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

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

  public void setPrenom(String prenom) {
    this.prenom = prenom;
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

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }


}
