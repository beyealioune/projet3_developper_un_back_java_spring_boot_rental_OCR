package com.rental.rental.services;

import com.rental.rental.Dtos.MessageDTO;
import com.rental.rental.entities.Message;
import com.rental.rental.entities.Rental;
import com.rental.rental.entities.User;
import com.rental.rental.repository.MessageRepository;
import com.rental.rental.repository.RentalRepository;
import com.rental.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    public MessageDTO createMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setMessage(messageDTO.getMessage());

        Rental rental = rentalRepository.findById(messageDTO.getRental_id()).orElse(null);
        message.setRental(rental);

        User user = userRepository.findById(messageDTO.getUser_id()).orElse(null);
        message.setUser(user);

        Message savedMessage = messageRepository.save(message);

        return MessageDTO.fromModel(savedMessage);
    }
}