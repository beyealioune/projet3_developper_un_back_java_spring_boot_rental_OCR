package com.rental.rental.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${base.imageUrl}")
    private String baseUrl;

    @Value("${image.upload.directory}")
    private String uploadDirectory;

    public String saveImage(MultipartFile picture, String destinationDirectory) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + picture.getOriginalFilename();
        fileName = fileName.replace(" ", "%20");
        String filePath = destinationDirectory + "/" + fileName;

        // Sauvegarder l'image sur le disque
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(picture.getBytes());
        }

        // Encoder le nom du fichier pour s'assurer que l'URL est valide
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);

        // Construire l'URL complète de l'image en utilisant l'URL de base et le nom du fichier encodé
        String imageUrl = baseUrl + "images/" + encodedFileName;
        return imageUrl;
    }




    public byte[] getImageData(String imageName) throws IOException {
        String imagePath = uploadDirectory + File.separator + imageName;
        File file = new File(imagePath);
        if (file.exists()) {
            return Files.readAllBytes(file.toPath());
        } else {
            return null;
        }
    }


}
