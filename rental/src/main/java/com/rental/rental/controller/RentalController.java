package com.rental.rental.controller;


import com.rental.rental.Dtos.RentalDTO;
import com.rental.rental.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Value("${image.upload.directory}")
    private String destinationDirectory;


    @PostMapping("/rentals")
    public ResponseEntity<RentalDTO> createRental(@ModelAttribute RentalDTO rentalDTO,
                                                  @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        RentalDTO createdRental = rentalService.createRental(rentalDTO, imageFile, destinationDirectory);
        return new ResponseEntity<>(createdRental, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDTO> updateRental(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) {
        RentalDTO updatedRental = rentalService.updateRental(id, rentalDTO);
        if (updatedRental != null) {
            return new ResponseEntity<>(updatedRental, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
