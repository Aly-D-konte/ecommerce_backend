package com.ecommerce.enkabutikiw.controllers;

import java.util.*;
import java.util.stream.Collectors;


import com.ecommerce.enkabutikiw.img.ConfigImage;
import com.ecommerce.enkabutikiw.img.Projetimage;
import com.ecommerce.enkabutikiw.models.*;
import com.ecommerce.enkabutikiw.payload.request.LoginRequest;
import com.ecommerce.enkabutikiw.payload.response.JwtResponse;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.RoleRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.security.jwt.JwtUtils;
import com.ecommerce.enkabutikiw.services.UserDetailsImpl;
import com.ecommerce.enkabutikiw.services.UserModifierService;
import com.ecommerce.enkabutikiw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import com.ecommerce.enkabutikiw.payload.request.SignupRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserService  userService;


  @Autowired
  UserModifierService userModifierService;
  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;


  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(),
                         userDetails.getNom(),
                         userDetails.getPrenom(),
            userDetails.getTelephone(),
            userDetails.getAdresse(),
            userDetails.getImage(),

            roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username existe déja!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email existe pour cet utilisateur!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getPrenom(),
            signUpRequest.getNom(),
            signUpRequest.getTelephone(),
            signUpRequest.getAdresse(),
            signUpRequest.getGenre(),
            signUpRequest.getImage());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;

          case "superadmin":
            Role Super_adminRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(Super_adminRole);

            break;

          case "livreur":
            Role livreurRole = roleRepository.findByName(ERole.ROLE_LIVREUR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(livreurRole);

            break;


        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }
    //String nomfile = StringUtils.cleanPath(signUpRequest.setImage(signUpRequest.).getOriginalFilename()) ;


    user.setRoles(roles);
  //  String uploaDir = "C:\\Users\\sadjo\\OneDrive\\Bureau\\ODK\\flutter_enkabutikiw\\flutter_frontend\\assets\\images";
   // ConfigImage.saveimg(uploaDir, nomfile, image);

    user.setImage("http://127.0.0.1/Images/avatar.jpg");
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PatchMapping("/modifierAvatar/{id}")
  public MessageResponse ModifierAvatar(@Param("file") MultipartFile file,
                                       @PathVariable("id") Long id){
    User user = new User();
    String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

    user.setImage(Projetimage.save(file, nomfile));

    return userModifierService.ModifierAvatar(user, id);

  }



  //::::::::::::::::::::::::::::::REINITIALISER PASSWORD::::::::::::::::::::::::::::::::::::::::::://

  @PostMapping("/resetPassword/{email}")
  public ResponseEntity<String> resetPassword(@PathVariable("email") String email) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      return new ResponseEntity<String>("Email non fourni", HttpStatus.BAD_REQUEST);
    }
    userService.resetPassword(user);
    return new ResponseEntity<String>("Email envoyé!", HttpStatus.OK);
  }

  //::::::::::::::::::::::::::::::::::::::::Changer mot de passe:::::::::::::::::::::::::::::::::::::::::::::::://

  @PostMapping("/changePassword")
  public ResponseEntity<String> changePassword(@RequestBody HashMap<String, String> request) {
    String emailOrTelephone= request.get("emailOrTelephone");
    User user = userRepository.findByTelephone(emailOrTelephone);

    if (user == null) {
      return new ResponseEntity<>("Utilisateur non fourni!", HttpStatus.BAD_REQUEST);
    }
    String currentPassword = request.get("currentpassword");
    String newPassword = request.get("newpassword");
    String confirmpassword = request.get("confirmpassword");
    if (!newPassword.equals(confirmpassword)) {
      return new ResponseEntity<>("PasswordNotMatched", HttpStatus.BAD_REQUEST);
    }
    String userPassword = user.getPassword();
    try {
      if (newPassword != null && !newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)) {
        if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
          userService.updateUserPassword(user, newPassword);
        }
      } else {
        return new ResponseEntity<>("IncorrectCurrentPassword", HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<>("Mot de passe changé avec succès!", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error Occured: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }



  @PutMapping("modifieruser/{id}")

  public MessageResponse modifieruser(@RequestBody User user, Long id){
    return  userModifierService.Modifier(user, id);

  }
  @GetMapping("liste")
  public  List<User> liste(){
    return userModifierService.liste();

  }



}
