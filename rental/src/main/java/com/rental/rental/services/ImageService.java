package com.rental.rental.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageService {

    public String saveImage(MultipartFile imageFile, String destinationDirectory) throws IOException {
        String fileName = imageFile.getOriginalFilename();
        String filePath = destinationDirectory + File.separator + fileName;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(imageFile.getBytes());
        }
        return filePath;
    }
}
