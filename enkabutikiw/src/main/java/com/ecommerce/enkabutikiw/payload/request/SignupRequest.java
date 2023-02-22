package com.ecommerce.enkabutikiw.payload.request;

import lombok.Data;

import java.util.Set;

import javax.validation.constraints.*;

@Data
public class SignupRequest {
  @Size(min = 3, max = 20)
  private String username;

  @Size(min = 3, max = 20)
  private String prenom;

  @Size(min = 3, max = 20)
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

  private Set<String> role;

  @Size(min = 6, max = 40)
  private String password;



}
