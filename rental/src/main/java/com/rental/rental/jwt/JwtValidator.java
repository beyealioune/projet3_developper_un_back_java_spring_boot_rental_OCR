package com.rental.rental.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtValidator {
    boolean validateToken(String token, UserDetails userDetails);
}