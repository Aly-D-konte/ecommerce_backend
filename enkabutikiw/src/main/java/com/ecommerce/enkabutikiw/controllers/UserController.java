package com.ecommerce.enkabutikiw.controllers;

import com.ecommerce.enkabutikiw.DTO.user.UserResponse;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @GetMapping("/userbyId/{id}")
    public UserResponse UserById(@PathVariable Long id){

        /////////////////////userRepository.findById(id).get();
        return userService.getUserById(id);
    }
}
