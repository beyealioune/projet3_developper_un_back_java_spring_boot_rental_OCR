package com.rental.rental.services;

import com.rental.rental.Dtos.UserDTO;
import com.rental.rental.entities.User;
import com.rental.rental.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserDTO getCurrentUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User not found with email: " + email);
        }
        return UserDTO.fromModel(user);
    }

    public UserDTO getMe() {
        // Récupérer l'email de l'utilisateur actuellement authentifié
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Recherche de l'utilisateur par email
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (!user.isPresent()) {
            throw new EntityNotFoundException("User not found with email: " + userEmail);
        }

        // Conversion de l'utilisateur en DTO
        return UserDTO.fromModel(Optional.of(user.get()));
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return UserDTO.fromModel(Optional.ofNullable(user));
    }
}