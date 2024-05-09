package com.rental.rental.Dtos;

import com.rental.rental.entities.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@Builder
public class RentalDTO {

    private Long id;
    private String name;
    private Double surface;
    private Double price;
    private String pictureUrl;
    private String description;
    private Long ownerId;

    public static RentalDTO fromModel(Rental rental) {
        return RentalDTO.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .pictureUrl(rental.getPictureUrl()) // Utiliser pictureUrl au lieu de picture
                .description(rental.getDescription())
                .ownerId(rental.getOwner() != null ? rental.getOwner().getId() : null) // Vérifier si owner est non null
                .build();
    }

}