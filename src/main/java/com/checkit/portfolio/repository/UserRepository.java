package com.checkit.portfolio.repository;

import com.checkit.portfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByDni(String dni);
    Optional<User> findByFirstName(String firstName);
    Optional<User> findByUsername(String username);
}
