package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
