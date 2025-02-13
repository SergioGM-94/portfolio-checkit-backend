package com.checkit.portfolio.controller;

import com.checkit.portfolio.model.User;
import com.checkit.portfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.UnionSubclass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("dni/{dni}")
    public ResponseEntity<User> findByDni(@PathVariable String dni) {
        Optional<User> user = userService.findByDni(dni);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
