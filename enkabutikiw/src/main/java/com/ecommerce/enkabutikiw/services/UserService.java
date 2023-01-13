package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.models.User;

public interface UserService {

    User findByUsername(String username);

}
