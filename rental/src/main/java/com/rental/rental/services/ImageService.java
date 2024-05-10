package com.rental.rental.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageService {

    public String saveImage(MultipartFile picture, String destinationDirectory) throws IOException {
        String fileName = picture.getOriginalFilename();
        String filePath = destinationDirectory + File.separator + fileName;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(picture.getBytes());
        }
        return filePath;
    }
}
