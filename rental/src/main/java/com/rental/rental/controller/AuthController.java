package com.rental.rental.controller;

import com.rental.rental.Dtos.UserDTO;
import com.rental.rental.entities.User;
import com.rental.rental.jwt.JwtUtil;
import com.rental.rental.repository.UserRepository;
import com.rental.rental.services.AuthService;
import com.rental.rental.services.RegisterService;
import com.rental.rental.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.HashMap;


@RestController
@RequestMapping("/api/")
public class AuthController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("auth/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());

        User registeredUser = registerService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("auth/login")
    public HashMap loginUser(@RequestBody UserDTO userDTO) {

        return authService.loginUser(userDTO);
    }

    @GetMapping("auth/me")
    public UserDTO getMe() {

        return userService.getMe();
    }

}