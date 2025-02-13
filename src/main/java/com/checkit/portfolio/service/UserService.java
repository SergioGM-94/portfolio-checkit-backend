package com.checkit.portfolio.service;

import com.checkit.portfolio.model.User;
import com.checkit.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByDni(String dni) {
        return userRepository.findByDni(dni);
    }

    public Optional<User> findByfirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public Optional<User> findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }
}
