package com.rental.rental.controller;

import com.rental.rental.Dtos.UserDTO;
import com.rental.rental.entities.User;
import com.rental.rental.jwt.JwtUtil;
import com.rental.rental.repository.UserRepository;
import com.rental.rental.services.RegisterService;
import com.rental.rental.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/auth/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());

        // Crypter le mot de passe avant de l'assigner à l'utilisateur
        String encryptedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        user.setPassword(encryptedPassword);

        return ResponseEntity.ok(registerService.register(user));
    }


    @PostMapping("/auth/login")
    public HashMap loginUser(@RequestBody UserDTO userDTO) {
        System.out.println("Login attempt for email: " + userDTO.getEmail());

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

            return response;
        } else {
            // Si l'utilisateur n'existe pas ou que le mot de passe est incorrect
            response.put("error", "Identifiants invalides");
            System.out.println("Login failed for email: " + userDTO.getEmail());
            return response;
        }
    }

    @GetMapping("/auth/me")
    public UserDTO getMe() {
        return userService.getMe();
    }


}