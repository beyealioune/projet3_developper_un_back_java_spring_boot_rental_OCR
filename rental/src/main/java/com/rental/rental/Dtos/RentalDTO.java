package com.rental.rental.Dtos;

import com.rental.rental.entities.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private Long owner_id;
    private Date created_at;
    private Date updated_at;


    public static RentalDTO modelToDto(Rental rental) {
        return RentalDTO.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .pictureUrl(rental.getPictureUrl()) // Utiliser pictureUrl au lieu de picture
                .description(rental.getDescription())
                .created_at(rental.getCreatedAt())
                .updated_at(rental.getUpdatedAt())
                .owner_id(rental.getOwner() != null ? rental.getOwner().getId() : null) // Vérifier si owner est non null
                .build();
    }


    public static List<RentalDTO> listFromModels(List<Rental> rentals) {
        return rentals.stream()
                .map(RentalDTO::fromModel)
                .collect(Collectors.toList());
    }

    public static RentalDTO fromModel(Rental rental) {
        return RentalDTO.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .pictureUrl(rental.getPictureUrl())
                .description(rental.getDescription())
                .created_at(rental.getCreatedAt())
                .updated_at(rental.getUpdatedAt())
                .owner_id(rental.getOwner() != null ? rental.getOwner().getId() : null)
                .build();
    }


}