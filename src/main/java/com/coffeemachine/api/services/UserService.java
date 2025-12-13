package com.coffeemachine.api.services;

import com.coffeemachine.api.models.User;
import com.coffeemachine.api.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registar novo utilizador com role USER por defeito
    public User registerUser(String username, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username já existe!");
        }

        String hashed = passwordEncoder.encode(rawPassword);
        User user = new User(username, hashed, User.Role.USER);
        return userRepository.save(user);
    }

    // Procurar utilizador por username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Validar password
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    // Obter ID do utilizador autenticado
    public Long findIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
    }
}