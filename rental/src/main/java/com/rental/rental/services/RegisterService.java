package com.rental.rental.services;

import com.rental.rental.entities.User;
import com.rental.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        // Implémentez ici la logique pour enregistrer un nouvel utilisateur
        return userRepository.save(user);
    }
}