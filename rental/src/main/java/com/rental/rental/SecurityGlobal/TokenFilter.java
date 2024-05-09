package com.rental.rental.SecurityGlobal;


import com.rental.rental.entities.User;
import com.rental.rental.jwt.JwtUtil;
import com.rental.rental.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class TokenFilter extends OncePerRequestFilter {


    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("mon filtre à été lancé");

        // récupérer token présent dans la requête, dans le header authorization
        String header = request.getHeader("Authorization");

        // vérifier qu'il y ait Bearer
        if (header != null && header.startsWith("Bearer")) {
            String jwt = header.substring(7);
            String mail = jwtUtil.extractUsername(jwt);

            // récupérer l'utilisateur par son email
            Optional<User> userOptional = userRepository.findByEmail(mail);

            // vérifier si l'utilisateur existe
            if (userOptional.isPresent()) {
                User user = userOptional.get();

                // valider le token
                if (jwtUtil.validateToken(jwt, (UserDetails) user)) {
                    // authentifier l'utilisateur
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, ((UserDetails) user).getAuthorities()));
                }
            }
        }

        // continuer la chaîne de filtres
        filterChain.doFilter(request, response);
    }

}
