package com.istep.Spring_Security.services;

import com.istep.Spring_Security.models.User;
import com.istep.Spring_Security.repositories.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User getUserById(Long id) {
        userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void updateUser(User user, Long id) {
        User userUp = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        userUp.setUsername(user.getUsername());
        userUp.setEmail(user.getEmail());
        userUp.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userUp.setRoles(user.getRoles());

        userRepository.flush();
    }
}