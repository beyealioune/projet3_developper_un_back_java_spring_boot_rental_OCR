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

/**
 * Service pour la gestion des locations, y compris la création, la mise à jour,
 * la récupération de toutes les locations et la récupération d'une location par ID.
 */
@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ImageService imageService;

    /**
     * Crée une nouvelle location en utilisant les informations fournies dans le DTO de location
     * et l'image associée.
     *
     * @param rentalDTO les informations de la location à créer.
     * @param picture l'image associée à la location.
     * @param destinationDirectory le répertoire de destination pour enregistrer l'image.
     * @return le DTO de la location créée.
     * @throws IOException si une erreur survient lors de la sauvegarde de l'image.
     */
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
                .map(rental -> {
                    RentalDTO rentalDTO = RentalDTO.fromModel(rental);
                    rentalDTO.setPictureUrl(imageService.getImageUrl(rental.getPictureUrl()));
                    return rentalDTO;
                })
                .collect(Collectors.toList());

        Map<String, List<RentalDTO>> response = new HashMap<>();
        response.put("rentals", rentalDTOs);
        return response;
    }
    public RentalDTO getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental != null) {
            RentalDTO rentalDTO = RentalDTO.fromModel(rental);
            rentalDTO.setPictureUrl(imageService.getImageUrl(rental.getPictureUrl()));
            return rentalDTO;
        }
        return null;
    }
}
