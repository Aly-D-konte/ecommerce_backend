package com.ecommerce.enkabutikiw.payload.request;

import lombok.Data;

import java.util.Set;

import javax.validation.constraints.*;

@Data
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(min = 3, max = 20)
  private String prenom;

  @NotBlank
  @Size(min = 3, max = 20)
  private String nom;

  @NotBlank
  @Size(max = 20)
  private String telephone;

  @NotBlank
  @Size(max = 20)
  private String adresse;

  @Size(max = 20)
  private String genre;


  private String image;



  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;



}
