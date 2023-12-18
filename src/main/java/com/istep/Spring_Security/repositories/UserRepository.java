package com.istep.Spring_Security.repositories;

import com.istep.Spring_Security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
