package com.nikatalks.controller;

import com.nikatalks.commons.dto.FileDocDto;
import com.nikatalks.service.FileDocService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileDocController {

    private final FileDocService fileDocService;

    public FileDocController(FileDocService fileDocService) {
        this.fileDocService = fileDocService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileDocDto> uploadFile(
    		@RequestParam("file") MultipartFile file, 
    		@RequestParam("language") String language,
            @RequestParam("competence") String competence) throws IOException {
        FileDocDto uploaded = fileDocService.uploadFile(file, language, competence);
        return ResponseEntity.ok(uploaded);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        byte[] data = fileDocService.downloadFile(id);

        // Aquí podrías obtener el contentType desde BD también
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FileDocDto>> listFiles() {
        List<FileDocDto> files = fileDocService.listFiles();
        return ResponseEntity.ok(files);
    }
    
    @GetMapping("/language/{language}")
    public ResponseEntity<List<FileDocDto>> getByLanguage(@PathVariable String language) {
        List<FileDocDto> result = fileDocService.getByLanguage(language);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }
    
    @GetMapping("/language/{language}/competence/{competence}")
    public ResponseEntity<List<FileDocDto>> getByLanguageAndCompetence(@PathVariable String language, 
    		@PathVariable String competence) {
        List<FileDocDto> result = fileDocService.getByLanguage(language);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }
    
    @GetMapping("/language/{language}/competence/{competence}/destination/{destination}")
    public ResponseEntity<List<FileDocDto>> findByLanguageCompetenceAndDestination(@PathVariable String language, 
    		@PathVariable String competence, @PathVariable String destination) {
        List<FileDocDto> result = fileDocService.findByLanguageCompetenceAndDestination(language, competence, destination);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @GetMapping("/competence/{competence}")
    public ResponseEntity<List<FileDocDto>> getByCompetence(@PathVariable String competence) {
        List<FileDocDto> result = fileDocService.getByCompetence(competence);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @GetMapping("/search-files")
    public ResponseEntity<List<FileDocDto>> findByLanguageDestinationLevelOrCompetency(
    		@RequestParam(name = "language", required = true) String language,
            @RequestParam(name = "destination", required = false) String destination,
            @RequestParam(name = "competence", required = false) String competence,
            @RequestParam(name = "level", required = false) String level) {
        List<FileDocDto> result = fileDocService.findByLanguageDestinationLevelOrCompetence(language, destination, competence, level);
        return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<FileDocDto> deleteFile(@PathVariable Long id) {
        FileDocDto deletedFile = fileDocService.deleteFile(id);
        return deletedFile == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(deletedFile);
    }
 
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file) {
        
        try {
            // Validaciones básicas
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo está vacío");
            }

            // Validar tipo de archivo
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body("El archivo debe ser una imagen");
            }
            
            // Llamar al servicio para subir la imagen
            String imageKey = fileDocService.uploadImage(file);
            
            // Retornar solo la imageKey como String
            return ResponseEntity.ok(imageKey);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al subir imagen: " + e.getMessage());
        }
    }

    /**
     * Endpoint para obtener URL de imagen - usado por otros microservicios
     */
    @GetMapping("/image-url/{imageKey}")
    public ResponseEntity<String> getImageUrl(@PathVariable String imageKey) {
        
        try {
            if (imageKey == null || imageKey.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("imageKey es requerida");
            }
            
            // Obtener URL desde el servicio
            String imageUrl = fileDocService.getImageUrl(imageKey);
            
            if (imageUrl != null) {
                // Retornar solo la URL como String
                return ResponseEntity.ok(imageUrl);
            } else {
                return ResponseEntity.notFound().build();
            }
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al obtener URL de imagen: " + e.getMessage());
        }
    }
    
    @GetMapping("/image-bytes")
    public ResponseEntity<byte[]> getImageBytes(@RequestParam("imageKey") String imageKey) {
        
        try {
            if (imageKey == null || imageKey.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            // Obtener URL desde el servicio
            byte[] imageUrl = fileDocService.getImageBytes(imageKey);
            
            if (imageUrl != null) {
                // Retornar solo la URL como String
                return ResponseEntity.ok(imageUrl);
            } else {
                return ResponseEntity.notFound().build();
            }
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Endpoint para eliminar imagen - usado por otros microservicios
     */
    @DeleteMapping("/delete-image")
    public ResponseEntity<String> deleteImage(@RequestParam("imageKey") String imageKey) {
        
        try {
            if (imageKey == null || imageKey.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("imageKey es requerida");
            }
            
            // Llamar al servicio para eliminar la imagen
            fileDocService.deleteImage(imageKey);
            
            return ResponseEntity.ok("Imagen eliminada correctamente");
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al eliminar imagen: " + e.getMessage());
        }
    }

}
