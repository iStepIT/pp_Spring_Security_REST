package com.istep.Spring_Security.services;

import com.istep.Spring_Security.models.User;
import com.istep.Spring_Security.repositories.UserRepository;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        User user = optionalUser.get();
        Hibernate.initialize(user.getRoles());
        return user;
    }
}