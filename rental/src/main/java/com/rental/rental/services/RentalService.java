package com.rental.rental.services;

import com.rental.rental.Dtos.RentalDTO;
import com.rental.rental.entities.Rental;
import com.rental.rental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ImageService imageService;

    public RentalDTO createRental(RentalDTO rentalDTO, MultipartFile imageFile, String destinationDirectory) throws IOException {
        Rental rental = new Rental();
        rental.setName(rentalDTO.getName());
        rental.setSurface(rentalDTO.getSurface());
        rental.setPrice(rentalDTO.getPrice());
        rental.setDescription(rentalDTO.getDescription());
        String picturePath = imageService.saveImage(imageFile, destinationDirectory);
        rental.setPictureUrl(picturePath);
        Rental savedRental = rentalRepository.save(rental);
        return RentalDTO.fromModel(savedRental);
    }

    public RentalDTO updateRental(Long id, RentalDTO rentalDTO) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental != null) {
            rental.setName(rentalDTO.getName());
            rental.setSurface(rentalDTO.getSurface());
            rental.setPrice(rentalDTO.getPrice());
            rental.setDescription(rentalDTO.getDescription());
            Rental updatedRental = rentalRepository.save(rental);
            return RentalDTO.fromModel(updatedRental);
        }
        return null;
    }

    public List<RentalDTO> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(RentalDTO::fromModel)
                .collect(Collectors.toList());
    }

    public RentalDTO getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental != null) {
            return RentalDTO.fromModel(rental);
        }
        return null;
    }
}
