package com.ecommerce.enkabutikiw.DTO.user;

import com.ecommerce.enkabutikiw.models.Panier;
import com.ecommerce.enkabutikiw.models.Produits;
import com.ecommerce.enkabutikiw.models.Role;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private long id;
    private String username;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String genre;
    private String image;
    private String adresse;
    private Panier panier;
    private List<Role> roles;

}
