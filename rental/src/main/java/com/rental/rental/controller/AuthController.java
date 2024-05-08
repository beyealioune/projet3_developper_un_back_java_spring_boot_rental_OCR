package com.rental.rental.controller;

import com.rental.rental.Dtos.UserDTO;
import com.rental.rental.entities.User;
import com.rental.rental.services.RegisterService;
import com.rental.rental.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private RegisterService registerService;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        return ResponseEntity.ok(registerService.register(user));
    }


}