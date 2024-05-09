package com.rental.rental.Dtos;

import com.rental.rental.entities.Message;
import com.rental.rental.entities.Rental;
import com.rental.rental.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MessageDTO {
    private Long id;
    private Long user_id;
    private Long rental_id;
    private String message;

    public static MessageDTO fromModel(Message message) {
        return MessageDTO.builder()
                .id(message.getId())
                .rental_id(message.getRental().getId())
                .user_id(message.getUser().getId())
                .message(message.getMessage())
                .build();
    }

}