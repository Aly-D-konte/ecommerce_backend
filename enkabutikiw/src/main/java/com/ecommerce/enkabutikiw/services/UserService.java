package com.ecommerce.enkabutikiw.services;

import com.ecommerce.enkabutikiw.DTO.user.UserResponse;
import com.ecommerce.enkabutikiw.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    User getByEmail(String email);

    public void resetPassword(User user);

    public void updateUserPassword(User user, String newPassword);

    User findByEmail(String userEmail);
    UserResponse modifier(User user, Long id);

    List<UserResponse> mapToUserList(List<User> userList);
    UserResponse mapToUser(User user);
    UserResponse getUserById(Long id);
}
