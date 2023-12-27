package com.istep.Spring_Security.repositories;

import com.istep.Spring_Security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    Optional<User> findByEmail(String email);

}
