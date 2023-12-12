package com.istep.Spring_Security.service;

import com.istep.Spring_Security.models.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    User getUserByName(String name);

    List<User> getUsers();

    void addUser(User user);

    void deleteUser(Long userId);

    void updateUser(User user, Long id);
}

