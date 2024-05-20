package com.rental.rental.services;

import com.rental.rental.Dtos.UserDTO;
import com.rental.rental.Exceptions.RegistrationException;
import com.rental.rental.entities.User;
import com.rental.rental.jwt.JwtUtil;
import com.rental.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

/**
 * Service gérant la logique d'authentification, y compris la connexion et la génération de jetons.
 */
@Service
public class AuthService {


    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Authentifie un utilisateur et génère un jeton JWT s'il est réussi.
     *
     * @param userDTO les informations de connexion de l'utilisateur.
     * @return une carte contenant le jeton JWT ou un message d'erreur.
     */
    public HashMap loginUser(UserDTO userDTO) {

        HashMap<String, String> response = new HashMap<>();

        // vérifier en base de donnée le mail fourni par l'utilisateur
        Optional<User> myUser =  userRepository.findByEmail(userDTO.getEmail());

        // si l'utilisateur existe et que le mot de passe correspond
        if(myUser.isPresent() && bCryptPasswordEncoder.matches(userDTO.getPassword(), myUser.get().getPassword())) {
            // Authentification de l'utilisateur et génération du token JWT
            String token = jwtUtil.generateToken(myUser.get());
            // Ajout du token à la réponse
            response.put("token", token);

            System.out.println("Login successful for email: " + userDTO.getEmail());
        } else {
            // Si l'utilisateur n'existe pas ou que le mot de passe est incorrect
            response.put("error", "Identifiants invalides");
            System.out.println("Login failed for email: " + userDTO.getEmail());
        }
        return response;
    }

}
