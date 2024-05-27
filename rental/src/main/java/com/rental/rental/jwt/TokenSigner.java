package com.rental.rental.jwt;

public interface TokenSigner {
    String signToken(String token);
}
