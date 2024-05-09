package com.rental.rental.Dtos;

import com.rental.rental.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String password;
    private Date createdAt;
    private Date updatedAt;

    public static UserDTO fromModel(Optional<User> user) {
        return UserDTO.builder()
                .id(user.get().getId())
                .email(user.get().getEmail())
                .name(user.get().getName())
                .password(user.get().getPassword())
                .createdAt(user.get().getCreatedAt())
                .updatedAt(user.get().getUpdatedAt())
                .build();
    }
}