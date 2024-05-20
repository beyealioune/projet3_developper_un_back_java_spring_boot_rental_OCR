package com.rental.rental.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

/**
 * Service pour la gestion des images, y compris l'enregistrement, la récupération des données et la génération d'URL.
 */
@Service
public class ImageService {

    @Value("${base.imageUrl}")
    private String baseUrl;

    @Value("${image.upload.directory}")
    private String uploadDirectory;

    /**
     * Sauvegarde une image téléchargée dans un répertoire spécifié et retourne l'URL relative de l'image.
     *
     * @param picture le fichier image téléchargé.
     * @param destinationDirectory le répertoire de destination où l'image sera sauvegardée.
     * @return l'URL relative de l'image.
     * @throws IOException si une erreur d'entrée/sortie se produit pendant la sauvegarde de l'image.
     */
    public String saveImage(MultipartFile picture, String destinationDirectory) throws IOException {
        // Générer un nom de fichier unique
        String fileName = UUID.randomUUID().toString() + "_" + picture.getOriginalFilename();
        fileName = fileName.replace(" ", "%20");

        // Chemin complet vers le fichier sur le serveur
        String filePath = destinationDirectory + "/" + fileName;

        // Sauvegarder l'image sur le disque
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(picture.getBytes());
        }

        // L'URL relative de l'image à partir du répertoire de destination
        String relativeImageUrl = "images/" + fileName;
        return relativeImageUrl;
    }



    /**
     * Récupère les données binaires de l'image spécifiée.
     *
     * @param imageName le nom de l'image à récupérer.
     * @return un tableau de bytes contenant les données de l'image, ou null si l'image n'existe pas.
     * @throws IOException si une erreur d'entrée/sortie se produit pendant la lecture de l'image.
     */

    public byte[] getImageData(String imageName) throws IOException {
        String imagePath = uploadDirectory + File.separator + imageName;
        File file = new File(imagePath);
        if (file.exists()) {
            return Files.readAllBytes(file.toPath());
        } else {
            return null;
        }
    }

    /**
     * Génère l'URL complète d'une image à partir de son nom.
     *
     * @param imageName le nom de l'image.
     * @return l'URL complète de l'image.
     */
    public String getImageUrl(String imageName) {

        return baseUrl  + imageName;
    }


}
