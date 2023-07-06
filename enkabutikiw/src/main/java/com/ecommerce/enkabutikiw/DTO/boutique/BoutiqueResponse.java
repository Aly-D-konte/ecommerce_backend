package com.ecommerce.enkabutikiw.DTO.boutique;

import com.ecommerce.enkabutikiw.models.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoutiqueResponse {
    private Long id;
    private String nom;
    private String description;
    private String adresse;
    private String image;
    private String imageDas;
    private boolean etat;
    private User user;
}
