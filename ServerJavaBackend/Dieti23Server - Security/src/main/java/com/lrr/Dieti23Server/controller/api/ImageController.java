package com.lrr.Dieti23Server.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Value("${upload.path}") // Configurazione del percorso di salvataggio nel file application.properties
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Il file caricato è vuoto");
        }

        try {
            // Genera un nome univoco per l'immagine
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // Salva l'immagine sulla directory definita
            Path imagePath = Paths.get(uploadPath + fileName);
            Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            // Restituisci un messaggio di conferma
            return ResponseEntity.ok().body("Immagine caricata con successo: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Si è verificato un errore durante il caricamento dell'immagine");
        }
    }


    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            // Carica l'immagine dal percorso definito
            Path imagePath = Paths.get(uploadPath + imageName);
            Resource resource = new UrlResource(imagePath.toUri());

            // Verifica se l'immagine esiste
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Ottieni il tipo MIME dell'immagine
            String contentType = new MimetypesFileTypeMap().getContentType(imagePath.toFile());

            // Restituisci l'immagine come risposta HTTP con il tipo MIME corretto
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
