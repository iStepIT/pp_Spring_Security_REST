package com.istep.Spring_Security.services;

import com.istep.Spring_Security.models.User;

import java.util.List;

public interface UserService {
    User getUserByName(String name);
    User getUserById(Long id);
    List<User> getAllUsers();
    void addUser(User user);

    void removeUser(Long userId);

    void updateUser(User user);

    User getCurrentUser();
}

