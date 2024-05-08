package com.rental.rental.Dtos;

import com.rental.rental.entities.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MessageDTO {
    private Long id;
    private Long rentalId;
    private Long userId;
    private String message;

    public static MessageDTO fromModel(Message message) {
        return MessageDTO.builder()
                .id(message.getId())
                .rentalId(message.getRental().getId())
                .userId(message.getUser().getId())
                .message(message.getMessage())
                .build();
    }

}