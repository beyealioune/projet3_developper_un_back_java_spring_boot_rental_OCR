package com.rental.rental.services;

import com.rental.rental.Dtos.RentalDTO;
import com.rental.rental.entities.Rental;
import com.rental.rental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ImageService imageService;

    public RentalDTO createRental(RentalDTO rentalDTO, MultipartFile picture, String destinationDirectory) throws IOException {
        Rental rental = new Rental();
        rental.setName(rentalDTO.getName());
        rental.setSurface(rentalDTO.getSurface());
        rental.setPrice(rentalDTO.getPrice());
        rental.setDescription(rentalDTO.getDescription());
        String picturePath = imageService.saveImage(picture, destinationDirectory);
        rental.setPictureUrl(picturePath);
        Rental savedRental = rentalRepository.save(rental);
        return RentalDTO.modelToDto(savedRental);
    }

    public RentalDTO updateRental(Long id, RentalDTO rentalDTO) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental != null) {
            rental.setName(rentalDTO.getName());
            rental.setSurface(rentalDTO.getSurface());
            rental.setPrice(rentalDTO.getPrice());
            rental.setDescription(rentalDTO.getDescription());
            Rental updatedRental = rentalRepository.save(rental);
            return RentalDTO.modelToDto(updatedRental);
        }
        return null;
    }

    public Map<String, List<RentalDTO>> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        List<RentalDTO> rentalDTOs = rentals.stream()
                .map(RentalDTO::fromModel)
                .collect(Collectors.toList());

        Map<String, List<RentalDTO>> response = new HashMap<>();
        response.put("rentals", rentalDTOs);
        return response;
    }

    public RentalDTO getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental != null) {
            return RentalDTO.fromModel(rental);
        }
        return null;
    }
}
