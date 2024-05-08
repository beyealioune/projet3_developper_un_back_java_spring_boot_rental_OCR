package com.rental.rental.services;

import com.rental.rental.Dtos.UserDTO;
import com.rental.rental.entities.User;
import com.rental.rental.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserDTO getCurrentUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User not found with email: " + email);
        }
        return UserDTO.fromModel(user);
    }
}