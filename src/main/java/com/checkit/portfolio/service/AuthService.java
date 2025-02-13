package com.checkit.portfolio.service;

import com.checkit.portfolio.jwt.JwtService;
import com.checkit.portfolio.model.RegisterRequest;
import com.checkit.portfolio.model.RegisterResponse;
import com.checkit.portfolio.model.User;
import com.checkit.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request) {
        String usernameSuggestion = "";

        boolean uniqueDni = true;
        boolean uniqueEmail = true;
        boolean uniqueUsername = true;

        Optional<User> existingDni = userRepository.findByDni(request.getDni());
        Optional<User> existingEmail = userRepository.findByEmail(request.getEmail());
        Optional<User> existingUsername = userRepository.findByUsername(request.getUsername());

        if(existingDni.isPresent()) {
            uniqueDni = !uniqueDni;
        }
        if(existingEmail.isPresent()) {
            uniqueEmail = !uniqueEmail;
        }
        if(existingUsername.isPresent()) {
            uniqueUsername = !uniqueUsername;
        }

        if(uniqueUsername == false) {
            usernameSuggestion = generateSuggestion(request.getUsername());
        }

        if(uniqueDni && uniqueEmail && uniqueUsername) {
            User user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .dni(request.getDni())
                    .username(request.getUsername())
                    .date(LocalDate.now())
                    .build();

            userRepository.save(user);

            return RegisterResponse.builder()
                    .token(jwtService.getToken(user))
                    .isDniUnique(uniqueDni)
                    .isEmailUnique(uniqueEmail)
                    .isUsernameUnique(uniqueUsername)
                    .suggestion(usernameSuggestion)
                    .build();
        } else {
            return RegisterResponse.builder()
                    .token("")
                    .isDniUnique(uniqueDni)
                    .isEmailUnique(uniqueEmail)
                    .isUsernameUnique(uniqueUsername)
                    .suggestion(usernameSuggestion)
                    .build();
        }
    }

    private String generateSuggestion(String base) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder suggestion = new StringBuilder(base);

        for (int i = 0; i < 4; i++) {
            suggestion.append(characters.charAt(random.nextInt(characters.length())));
        }

        return suggestion.toString();
    }
}
